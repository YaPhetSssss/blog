package com.blog.darkvilla.Service;

import com.blog.darkvilla.DTO.BlogDTO;
import com.blog.darkvilla.DTO.PaginationDTO;
import com.blog.darkvilla.Map.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("pageService")
public class PageService {
    @Autowired
    private BlogMapper blogMapper;

    private int totalPage;
    private final int pageSize = 8;
    private int totalBlog;
    private int page;
    private List<Integer> pages = new ArrayList<>();

    public PaginationDTO getPaginationForIndex(String currentPage) {
        page = Integer.parseInt(currentPage);
        totalBlog = blogMapper.getCount();
        totalPage = (int) (Math.ceil((double) totalBlog / pageSize));
        if (page > totalPage) page = totalPage;
        if (page < 0) page = 1;
        List<BlogDTO> list = blogMapper.findBlogWithUser(pageSize, (page - 1) * pageSize);
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setList(list);
        paginationDTO.setTotalPage(totalPage);
        //上一页按钮
        if (page == 1) paginationDTO.setShowPrevious(false);
        else paginationDTO.setShowPrevious(true);
        //下一页按钮
        if (page == totalPage) paginationDTO.setShowNext(false);
        else paginationDTO.setShowNext(true);
        //最前页按钮
        if(totalPage < 7)   paginationDTO.setShowFirst(false);
        else if (page > 4) paginationDTO.setShowFirst(true);
        else paginationDTO.setShowFirst(false);
        //最后页按钮
        if(totalPage < 7)   paginationDTO.setShowEnd(false);
        else if (page < totalPage - 3) paginationDTO.setShowEnd(true);
        else paginationDTO.setShowEnd(false);
        paginationDTO.setCurrentPage(page);
        //所展示页数
        pages.clear();
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }
        paginationDTO.setPages(pages);
        return paginationDTO;
    }
    public PaginationDTO getPaginationForUser(String userId, String currentPage) {
        page = Integer.parseInt(currentPage);
        totalBlog = blogMapper.getCountForUser(userId);
        totalPage = (int) (Math.ceil((double) totalBlog / pageSize));
        if (page > totalPage) page = totalPage;
        if (page < 0) page = 1;
        List<BlogDTO> list = blogMapper.findBlogByUser(userId, pageSize, (page - 1) * pageSize );
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setList(list);
        paginationDTO.setTotalPage(totalPage);
        //上一页按钮
        if (page == 1) paginationDTO.setShowPrevious(false);
        else paginationDTO.setShowPrevious(true);
        //下一页按钮
        if (page == totalPage) paginationDTO.setShowNext(false);
        else paginationDTO.setShowNext(true);
        //最前页按钮
        if(totalPage < 7)   paginationDTO.setShowFirst(false);
        else if (page > 4) paginationDTO.setShowFirst(true);
        else paginationDTO.setShowFirst(false);
        //最后页按钮
        if(totalPage < 7)   paginationDTO.setShowEnd(false);
        else if (page < totalPage - 3) paginationDTO.setShowEnd(true);
        else paginationDTO.setShowEnd(false);
        paginationDTO.setCurrentPage(page);
        //所展示页数
        pages.clear();
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }
        paginationDTO.setPages(pages);
        return paginationDTO;
    }
}
