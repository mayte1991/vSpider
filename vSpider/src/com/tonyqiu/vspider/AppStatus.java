package com.tonyqiu.vspider;

import java.util.HashSet;
import java.util.Set;

import org.junit.internal.matchers.IsCollectionContaining;

class AppStatus {
//	public static int ALL_THREAD_STOP= 0x0;
//	public static int LIST_PAGE_THREAD_RUNNING = 0x1;
//	public static int DETAIL_PAGE_THREAD_RUNNING = 0x2;
//	public static int IMAGE_DOWNLOAD_THREAD_RUNNING = 0x4;
	
	private int listPageThreadRunning = 0;
	private Set<Thread> listPageRunningThreadSet = new HashSet<Thread>();
	
	private int detailPageThreadRunning = 0;
	private Set<Thread> detailPageRunningThreadSet = new HashSet<Thread>();
	
	private int imageDownloadThreadRunning = 0;
	private Set<Thread> imageDownloadRunningThreadSet = new HashSet<Thread>();
	
	private int contentDbSaverRunning = 0;
	private Set<Thread> contentDbSaverRunningThreadSet = new HashSet<Thread>();
	
	public boolean isListPageThreadRunning() {
		return listPageThreadRunning!=0;
	}
	public void addListPageThreadRunning(Thread t) {
		if(!this.listPageRunningThreadSet.contains(t)) {
			this.listPageRunningThreadSet.add(t);
			this.listPageThreadRunning +=1;
		}
	}
	public void removeListPageThreadRunning(Thread t) {
		if(this.listPageRunningThreadSet.contains(t)) {
			this.listPageRunningThreadSet.remove(t);
			this.listPageThreadRunning -=1;
		}
	}
	public boolean isDetailPageThreadRunning() {
		return detailPageThreadRunning != 0;
	}
	public void addDetailPageThreadRunning(Thread t) {
		if(!this.detailPageRunningThreadSet.contains(t)) {
			this.detailPageRunningThreadSet.add(t);
			this.detailPageThreadRunning += 1;
		
		}
	}
	public void removeDetailPageThreadRunning(Thread t) {
		if(this.detailPageRunningThreadSet.contains(t)) {
			this.detailPageRunningThreadSet.remove(t);
			this.detailPageThreadRunning -= 1;
		
		}
	}
	public boolean isImageDownloadThreadRunning() {
		return imageDownloadThreadRunning !=0;
	}
	public void addImageDownloadThreadRunning(Thread t) {
//		System.out.println(t);
		if(!this.imageDownloadRunningThreadSet.contains(t)) {
			this.imageDownloadRunningThreadSet.add(t);
			this.imageDownloadThreadRunning += 1;
		}
	}
	public void removeImageDownloadThreadRunning(Thread t) {
//		System.out.println(t);
		if(this.imageDownloadRunningThreadSet.contains(t)) {
			this.imageDownloadRunningThreadSet.remove(t);
			this.imageDownloadThreadRunning -= 1;
		}
	}
	
	public void addContentDbSaverRunning(Thread t) {
//		System.out.println(t);
		if(!this.contentDbSaverRunningThreadSet.contains(t)) {
			this.contentDbSaverRunningThreadSet.add(t);
			this.contentDbSaverRunning += 1;
		}
	}
	public void removeContentDbSaverRunning(Thread t) {
//		System.out.println(t);
		if(this.contentDbSaverRunningThreadSet.contains(t)) {
			this.contentDbSaverRunningThreadSet.remove(t);
			this.contentDbSaverRunning -= 1;
		}
	}
	public boolean isContentDbSaverRunning() {
		return contentDbSaverRunning !=0;
	}
	public boolean isAllThreadStop() {
		if(listPageThreadRunning == 0
				&& detailPageThreadRunning == 0
				&& imageDownloadThreadRunning == 0
				&& contentDbSaverRunning == 0
				) {
			return true;
		}
		return false;
			
	}
	
	public String toString() {
		return String.format("L:%s(%s),D:%s(%s),I:%s(%s),D:%s(%s)", 
				isListPageThreadRunning(),listPageThreadRunning,
				isDetailPageThreadRunning(),detailPageThreadRunning,
				isImageDownloadThreadRunning(),imageDownloadThreadRunning,
				isContentDbSaverRunning(), contentDbSaverRunning
				);
	}
}