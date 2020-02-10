package com.ego.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/search")
public class SearchController {

    /**
     * 跳转搜索页面
     *
     * @param searchStr
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request, String searchStr, Model model) {
        try {
            // 对输入的内容进行编码，防止中文乱码
            searchStr = URLEncoder.encode(searchStr, "UTF-8");
            System.out.println(searchStr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "redirect:"
                + request.getSession().getServletContext().getAttribute("portalUrl")
                + "search/index?searchStr=" + searchStr;
    }

}
