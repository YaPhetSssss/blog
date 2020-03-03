package com.blog.darkvilla.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PaginationDTO{
    private List<BlogDTO> list;
    private Integer currentPage;
    private List<Integer> pages;
    private Boolean showPrevious;
    private Boolean showNext;
    private Boolean showFirst;
    private Boolean showEnd;
    private Integer totalPage;
}
