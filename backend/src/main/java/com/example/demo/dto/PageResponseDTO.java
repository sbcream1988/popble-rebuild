package com.example.demo.dto;

import java.util.List;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageResponseDTO<E> {

	private List<E> dtoList;
	private List<Integer> pageNumList;
	
	private int currentPage; //현재 페이지
	private int totalPage; //총 페이지
	
	private boolean hasPrev;
	private boolean hasNext;
	private int prevPage;
	private int nextPage;
	
	public PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, long totalCount) {
		this.dtoList = dtoList;
		this.currentPage = pageRequestDTO.getPage();
		
		int pageBlockSize = 5;
		
		int end = (int)(Math.ceil(currentPage/(double)pageBlockSize)) * pageBlockSize;
		int start = end - (pageBlockSize-1);
		
		int last = (int)(Math.ceil(totalCount/(double)pageRequestDTO.getSize()));
		end = Math.min(end, last);
		
		this.hasPrev = start > 1;
		this.hasNext = last > end;
		
		if(hasPrev) {
			this.prevPage = start -1;
		}
		if(hasNext) {
			this.nextPage = end + 1;	
		}
		
		this.pageNumList = IntStream.rangeClosed(start, end).boxed().toList();
		this.totalPage = last;
	}
}
