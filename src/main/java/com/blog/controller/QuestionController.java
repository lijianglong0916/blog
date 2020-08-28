package com.blog.controller;/*
 *@author:
 *@time
 */

import com.blog.dto.CommentToHtmlDto;
import com.blog.dto.ListQuestionDto;
import com.blog.dto.QuestionDto;
import com.blog.entity.Comment;
import com.blog.entity.Question;
import com.blog.entity.User;
import com.blog.service.*;
import com.blog.utils.BlogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @Autowired
    private FabulousService fabulousService;

    @Autowired
    private HeadPictureService headPictureService;

    @Value("${default-head-picture}")
    private String defaultHp;

    @GetMapping("queDetail")
    public ModelAndView queDetail(@RequestParam(name = "questionId") String questionId,
                                  HttpSession session,
                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                  @RequestParam(name = "listNumber", defaultValue = "8") int listNumber) {
        ModelAndView view = new ModelAndView("questionDetail");


        QuestionDto question = questionService.getQuestionByQueId(Integer.parseInt(questionId));
        if (!BlogUtils.isObjectNull(questionId, question)) {
            //获取所有回复信息
            List<Comment> comments = commentService.selectCommentByQuestionId(question.getId());
            //获取该问题下评论总数
            int commentNumber = comments.size();
            List<CommentToHtmlDto> commentToHtmlDtos = new ArrayList<>();
            for (Comment comment : comments) {
                commentToHtmlDtos.add(convert(comment));
            }
            //获取发起question的用户账号
            Long creatorId = question.getCreatorId();
            //通过用户账号获取用户信息
            User user = userService.getUserByAccountId(creatorId);
            //获取文章作者name ,头像
            String creatorName = user.getName();
            String creatorHeadP = headPictureService.getHPByUserAccount(user.getAccount_id()).getPicture_url();
            //根据tag获取相关问题
            String[] tags = question.getTag().split(",");
            List<Question> relatedQuestions = getRelatedQuestions(tags);
            view.addObject("relatedQuestions", relatedQuestions);
            view.addObject("creatorHeadP", creatorHeadP);
            view.addObject("question", question);
            view.addObject("creatorName", creatorName);
            view.addObject("commentToHtmlDtos", commentToHtmlDtos);
            view.addObject("commentNumber", commentNumber);
            questionService.updateQuestionReadCount(question.getRead_count() + 1, question.getId());

            //查看当前用户是否已登录
            Long userAccount = (Long) session.getAttribute("userAccount");
            //如果用户已登录
            if (userAccount != null) {
                //获取当前登录的用户名
                String currentUserName = (String) session.getAttribute("userName");
                boolean isLike = isUserLikeThisQuestion(question.getId(), userAccount);
                String giveLike = null;
                if (isLike) {
                    giveLike = "已赞";
                } else {
                    giveLike = "点赞";
                }

                //获取当前登录的用户头像
                String currentHeadP = headPictureService.getHPByUserAccount(userAccount).getPicture_url();
                view.addObject("currentHeadP", currentHeadP);
                view.addObject("currentUserName", currentUserName);
                view.addObject("giveLike", giveLike);
                return view;
            } else {//无用户登录，游客进入
                String currentUserName = "未登录";
                String giveLike = "点赞";
                //获取默认的用户头像
                String currentHeadP = defaultHp;
                //根据tag获取相关问题

                view.addObject("currentHeadP", currentHeadP);
                view.addObject("currentUserName", currentUserName);
                view.addObject("giveLike", giveLike);
                return view;
            }


        } else {
            //如果查询的问题为空则返回首页
            ListQuestionDto listQuestionDto = questionService.getListQuestions(page, listNumber);
            //获取热门问题
            List<Question> hotQuestions = getLikeQues();
            view.addObject("hotQuestions", hotQuestions);
            view.addObject("listQuestionDto", listQuestionDto);
            view.setViewName("redirect:/");
        }
        return view;
    }

    /**
     * 全局查询显示
     *
     * @param searchQuestion
     * @param page
     * @param listNumber
     * @return
     */
    @GetMapping("inputSearch")
    public ModelAndView inputSearch(@RequestParam(name = "searchQuestion") String searchQuestion,
                                    @RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "listNumber", defaultValue = "8") int listNumber) {
        ModelAndView view = new ModelAndView("search");
        ListQuestionDto questions = null;
        try {
            //查询到的问题
            questions = questionService.getQuestionsByMsg(searchQuestion, page, listNumber);
            //获取热门问题
            List<Question> hotQuestions = getLikeQues();
            view.addObject("hotQuestions", hotQuestions);
            view.addObject("questions", questions);
            view.addObject("msg", "查询成功");
            return view;
        } catch (RuntimeException e) {
            e.printStackTrace();
            view.addObject("msg", "数据库操作失败");
            return view;
        }
    }


    /**
     * 删除问题,同时删除评论点赞等信息，添加事务
     *
     * @param questionDto
     * @param model
     * @return
     */
    @PostMapping("deleteQuestion")
    @ResponseBody
    @Transactional
    public String deleteQuestion(@RequestBody QuestionDto questionDto, Model model) {
        if (!BlogUtils.isObjectNull(questionDto.getId())) {
            //获取该question下的所有comment并删除
            List<Comment> comments = commentService.selectCommentByQuestionId(questionDto.getId());
            if (comments.size() > 0) {
                for (Comment comment : comments) {
                    commentService.deleteComment(comment.getId());
                }
            }

            //删除question
            questionService.deleteQueById(questionDto.getId());
            //移除所有赞信息
            fabulousService.removeFabulous(questionDto.getId());
        }
        //返回主页
        ListQuestionDto listQuestionDto = questionService.getListQuestions(1, 8);
        Question[] likeQues = questionService.getLikeQues();
        List<Question> hotQuestions = new ArrayList<>();
        for (Question likeQue : likeQues) {
            hotQuestions.add(likeQue);
        }
        model.addAttribute("hotQuestions", hotQuestions);
        model.addAttribute("listQuestionDto", listQuestionDto);
        return "success";
    }

    /**
     * 使用comment工具类转换为前端需要的数据
     *
     * @param comment
     * @return
     */
    public CommentToHtmlDto convert(Comment comment) {
        CommentToHtmlDto commentToHtmlDto = new CommentToHtmlDto();
        commentToHtmlDto.setComment(comment);
        //根据创建回复的user id获取user
        User user = userService.getUserByAccountId(comment.getCreatorId());
        commentToHtmlDto.setUser(user);
        return commentToHtmlDto;
    }

    /**
     * 判断当前用户是否为该文章点过赞
     *
     * @param question_id
     * @param user_id
     * @return
     */
    public boolean isUserLikeThisQuestion(int question_id, Long user_id) {
        boolean isFabulous = fabulousService.isFabulousExist(question_id, user_id);
        return isFabulous;
    }

    /**
     * 利用tag获取8条点赞最多的问题，作为相关问题返回
     *
     * @param tags
     * @return
     */
    public List<Question> getRelatedQuestions(String[] tags) {
        List<Question> questionList = new ArrayList<>();
        int len = 0;
        for (int i = len; i < tags.length; i++) {
            Question[] likeQueByTag = questionService.getLikeQueByTag(tags[i]);
            for (Question question1 : likeQueByTag) {
                questionList.add(question1);
                len++;
                if (len == 8) {
                    break;
                }
            }
            if (len == 8) {
                break;
            }
        }
        return questionList;
    }

    /**
     * 获取点赞最多的12条question
     *
     * @return
     */
    private List<Question> getLikeQues() {
        Question[] likeQues = questionService.getLikeQues();
        List<Question> hotQuestions = new ArrayList<>();
        for (Question likeQue : likeQues) {
            hotQuestions.add(likeQue);
        }
        return hotQuestions;
    }
}

