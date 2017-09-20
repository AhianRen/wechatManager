package cn.chat.wechat.pojo.response;

import java.util.List;

public class NewsMessageResp extends BaseMessageAttribute{
	private int ArticleCount;//图文消息个数，限制为8条以内
	
	private List<Article> article;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticle() {
		return article;
	}

	public void setArticle(List<Article> article) {
		this.article = article;
	}
	
}
