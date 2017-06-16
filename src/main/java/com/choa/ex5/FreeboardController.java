package com.choa.ex5;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choa.board.BoardDTO;
import com.choa.freeboard.FreeboardDTO;

import com.choa.freeboard.FreeboardServiceImpl;

@Controller
@RequestMapping(value="/freeboard/**")
public class FreeboardController {
	@Inject
	private FreeboardServiceImpl freeboardService;
	
	/*@RequestMapping(value="freeboardWrite")
	public void write(Model model) throws Exception{
		
		model.addAttribute("path", "write");
	}
	@RequestMapping(value="freeboardWrite")
	public String write(FreeboardDTO freeboardDTO,Model model, RedirectAttributes rd) throws Exception{
		
		int result = freeboardService.boardWrite(freeboardDTO);
		String message = "fail";
		if(result>0){
			message = "SUCCESS";
		}
		rd.addFlashAttribute("message", message);
		return "redirect:freeboardList";
		
	}
	@RequestMapping(value="freeboardView")
	public void view(Integer num)throws Exception{
		freeboardService.boardView(num);
	}
	@RequestMapping(value="freeboardDelete")
	public void delete(Integer num)throws Exception{
		freeboardService.boardDelete(num);
	}
	*/
	@RequestMapping(value="freeboardList")
	public String boardList(@RequestParam(defaultValue="1")Integer curPage, Model model)throws Exception{
		List<BoardDTO> ar = freeboardService.boardList(curPage);
		model.addAttribute("list", ar);
		model.addAttribute("board","freeboard");
		System.out.println("List로 가자");
		return "board/boardList";
	}
	/*@RequestMapping(value="freeboardUpdate")
	public void update(FreeboardDTO freeboardDTO)throws Exception{
		freeboardService.boardUpdate(freeboardDTO);
	}
	@RequestMapping(value="freeboardUpdate")
	public void update(Model model, int num)throws Exception{
		
	}
*/	 
	


}
