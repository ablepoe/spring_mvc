package com.entity;

/**
 * 分页管理类
 * @author hanliang
 *
 */
public class Pagination {

	private int totalCounts;
	private int basePagi;
	private int totalPages;
	private int currentPage;
	private int baseSearch;
	
	public int getTotalCounts() {
		return totalCounts;
	}
	public void setTotalCounts(int totalCounts) {
		this.totalCounts = totalCounts;
		
		this.init();
		
	}
	public int getBasePagi() {
		return basePagi;
	}
	public void setBasePagi(int basePagi) {
		this.basePagi = basePagi;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getBaseSearch() {
		return baseSearch;
	}
	public void setBaseSearch(int baseSearch) {
		this.baseSearch = baseSearch;
	}
	
	private void init(){
		if(this.basePagi == 0){
			this.basePagi = 3;	
		}
		this.totalPages = this.totalCounts % this.basePagi == 0 ? this.totalCounts / this.basePagi : this.totalCounts / this.basePagi + 1;
		if(this.currentPage < 1){
			this.currentPage = 1;
		}
		if(this.currentPage > this.totalPages){
			this.currentPage = this.totalPages;
		}
		this.baseSearch =  this.totalPages == 0 ? 0 : (currentPage - 1) * basePagi;				
	}
}
