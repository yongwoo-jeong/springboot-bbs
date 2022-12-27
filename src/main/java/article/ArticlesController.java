package article;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ArticlesController {
//	@RequestMapping("/")
//	public String HandleHome(HttpServletRequest req){
//		List<ArticleVO> searchedArticles = new ArticleDAO().searchArticles();
//		System.out.println("HELLO");
//		req.setAttribute("articles", searchedArticles);
//		req.setAttribute("id", "this is ID");
//		return "index";
//	}
}
