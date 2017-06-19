package com.choa.ex5;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choa.board.BoardDTO;
import com.choa.freeboard.FreeboardDAOImpl;
import com.choa.freeboard.FreeboardDTO;

import com.choa.freeboard.FreeboardServiceImpl;

@Controller
@RequestMapping(value="/freeboard/**")
public class FreeboardController {
	@Inject
	private FreeboardServiceImpl freeboardService;
	
	@RequestMapping(value="freeboardWrite",method=RequestMethod.GET)
	public void write(Model model) throws Exception{
		
		model.addAttribute("path", "Write");
	}
	@RequestMapping(value="freeboardWrite",method=RequestMethod.POST)
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
	public void view(Integer num, Model model)throws Exception{
		FreeboardDTO freeboardDTO =(FreeboardDTO) freeboardService.boardView(num);
		model.addAttribute("dto", freeboardDTO);
	}
	@RequestMapping(value="freeboardDelete")
	public String delete(Integer num)throws Exception{
		freeboardService.boardDelete(num);
		return "redirect:/freeboard/freeboardList";
	}
	
	@RequestMapping(value="freeboardList")
	public String boardList(@RequestParam(defaultValue="1")Integer curPage, Model model)throws Exception{
		List<BoardDTO> ar = freeboardService.boardList(curPage);
		model.addAttribute("list", ar);
		model.addAttribute("board","freeboard");
		System.out.println("List로 가자");
		return "board/boardList";
	}
	@RequestMapping(value="freeboardUpdate",method=RequestMethod.POST)
	public String update(FreeboardDTO freeboardDTO, RedirectAttributes rd)throws Exception{
		freeboardService.boardUpdate(freeboardDTO);
		return "redirect:freeboardList";
	}
	@RequestMapping(value="freeboardUpdate",method=RequestMethod.GET)
	public String update(Model model, int num)throws Exception{
		FreeboardDTO dto = (FreeboardDTO)freeboardService.boardView(num);
		model.addAttribute("dto", dto);
		model.addAttribute("path", "Update");
		return "freeboard/freeboardWrite";
	}
	 
	


}
