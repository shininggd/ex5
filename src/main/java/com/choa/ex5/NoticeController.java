 package com.choa.ex5;


import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.choa.notice.NoticeDTO;
import com.choa.notice.NoticeService;


@Controller
@RequestMapping(value="/notice/**")
public class NoticeController {
	@Inject//type
	private NoticeService noticeService;
	
	
	@RequestMapping(value="notice/test")
	public void test(){
		System.out.println(noticeService);
		noticeService.test();
	}
	
	/*폼으로 감*/
	@RequestMapping(value="noticeWrite",method=RequestMethod.GET)
	public void write(Model model){
		System.out.println("Write로 가자");
		model.addAttribute("path", "Write");
	}
	
	/*프로세스로 감*/
	@RequestMapping(value="noticeWrite",method=RequestMethod.POST)
	public String write(NoticeDTO noticeDTO, Model model, RedirectAttributes rd)throws Exception{
		int result = noticeService.noticeWrite(noticeDTO);
		System.out.println("WriteP로 가자");
		String message = "FAIL";
		if(result > 0){
			message = "SUCCESS";
		}
		/*model.addAttribute("message", message);*/
		rd.addFlashAttribute("message", message);//리다이렉트에 어트리뷰트를 추가하되, 주소창에는 안보이게 하는 판타스틱한 방법
			return "redirect:noticeList";
		
		
		
	}
	/*폼으로 감*/
	@RequestMapping(value="noticeUpdate",method=RequestMethod.GET)
	public String update(int num, Model model) throws Exception{
		NoticeDTO noticeDTO = noticeService.noticeView(num);
		System.out.println("Update로 가자");
		model.addAttribute("dto", noticeDTO);
		model.addAttribute("path", "Update");
		return "notice/noticeWrite";
		
	}
	
	
	/*프로세스로 감*/
	@RequestMapping(value="noticeUpdate",method=RequestMethod.POST)
	public String update(NoticeDTO noticeDTO,  RedirectAttributes redirectAttributes)throws Exception{
		int result = noticeService.noticeUpdate(noticeDTO);
		String message = "FAIL수정";
		if(result>0){
			message = "SUCCESS수정";
		}
		redirectAttributes.addFlashAttribute("message", message);
		System.out.println("UpdateP로 가자");
		return "redirect:/notice/noticeList";
		
	}
	//Delete
	@RequestMapping(value="noticeDelete")
	public String delete(Integer num, RedirectAttributes redirectAttributes)throws Exception{
		
		int result = noticeService.noticeDelete(num);
		
		String message = "FAIL삭제";
		if(result>0){
			message = "SUCCESS삭제";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/notice/noticeList";
		

	}
	
	//View
	@RequestMapping(value="noticeView")
	public void view(Integer num, Model model) throws Exception{
		
		NoticeDTO noticeDTO = noticeService.noticeView(num);
		System.out.println("View로 가자");
		model.addAttribute("dto", noticeDTO);
	}
	
	
	//List
	@RequestMapping(value="noticeList")
	public void list(Model model, @RequestParam(defaultValue="1") Integer curPage) throws Exception{
		List<NoticeDTO> ar = noticeService.noticeList(curPage);
		model.addAttribute("list", ar);
		System.out.println("List로 가자");
	}

}
