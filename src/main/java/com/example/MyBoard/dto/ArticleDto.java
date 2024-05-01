package com.example.MyBoard.dto;


import com.example.MyBoard.entity.Article;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ArticleDto {
    private Long id;
    @Size(max = 20,min = 1, message = "제목을 입력해주세요")
    private String title;
    @Size(max = 200,min = 1, message = "내용을 입력해주세요")
    private String content;

    public static ArticleDto fromEntity(Article article){
        return new ArticleDto(article.getId(), article.getTitle(), article.getContent());
    }
    public static Article fromDto(ArticleDto articleDto){
        Article article = new Article();
        article.setId(articleDto.getId());
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
        return article;
    }
}
