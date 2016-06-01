package com.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.Exposer;
import com.dto.SeckillExecution;
import com.dto.SeckillResult;
import com.entity.Seckill;
import com.enums.SeckillStateEnum;
import com.exception.RepeatKillException;
import com.exception.SeckillCloseException;
import com.exception.SeckillException;
import com.service.SeckillService;

/**
 * 
 * @author hanliang
 * seckill 请求控制层
 */
@Controller
@RequestMapping(value = "/seckill") //第一级请求目录 
public class SeckillController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="SeckillService")
	private SeckillService seckillService;
	
	/**
	 * 返回所有秒杀列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String getList(Model model){
		List<Seckill> li = seckillService.getSeckillList();
		model.addAttribute("list", li);
		return "list";
	}
	
	/**
	 * 请求指定id的秒杀对象
	 * @param seckill_id
	 * @return
	 */
	@RequestMapping(value = "/{seckill_id}/detail" , method = RequestMethod.GET)
	public String getSeckillById(@PathVariable("seckill_id") Long seckill_id, Model model){
		if(seckill_id == null){
			return "redirect:/seckill/list";
		}
		Seckill seckill = seckillService.getSeckillById(seckill_id);
		if(seckill == null){
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill", seckill);
		return "detail";
	}
	
	/**
	 * 暴露秒杀接口
	 * @param seckill_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{seckill_id}/exposor", produces="application/json;charset=UTF-8", method = RequestMethod.POST)
	public SeckillResult<Exposer> exportSeckillUrl(@PathVariable("seckill_id") Long seckill_id){
		try{
			Exposer exposer = seckillService.exportSeckillUrl(seckill_id);
			return new SeckillResult<Exposer>(true, exposer);
		}catch (Exception e){
			log.error(e.getMessage(), e);
			return new SeckillResult<Exposer>(false, e.getMessage());
		}
	}
	
	/**
	 * 执行秒杀
	 * @param seckill_id
	 * @param md5
	 * @param user_phone
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{seckill_id}/{md5}/execution", produces="application/json;charset=UTF-8", method = RequestMethod.POST)
	public SeckillResult<SeckillExecution> executeSeckill(@PathVariable("seckill_id") Long seckill_id, @PathVariable("md5") String md5, @CookieValue(value="kill_phone", required=false) Long user_phone){
		try{
			SeckillExecution seckillExecution = seckillService.executeSeckill(seckill_id, user_phone, md5);
			return new SeckillResult<SeckillExecution>(true, seckillExecution);
		} catch (SeckillCloseException e1){
			SeckillExecution seckillExecution = new SeckillExecution(seckill_id, SeckillStateEnum.END);
			return new SeckillResult<SeckillExecution>(false, seckillExecution);
		} catch (RepeatKillException e2){
			SeckillExecution seckillExecution = new SeckillExecution(seckill_id, SeckillStateEnum.REPEAT_KILL);
			return new SeckillResult<SeckillExecution>(false, seckillExecution);
		} catch (SeckillException e){
			SeckillExecution seckillExecution = new SeckillExecution(seckill_id, SeckillStateEnum.INNER_ERROR);
			return new SeckillResult<SeckillExecution>(false, seckillExecution);
		}
	}
	
	/**
	 * 返回系统时间
	 * @return
	 */
	@RequestMapping(value = "/time/now", method = RequestMethod.GET)
	public SeckillResult<Long> time(){
		Date date = new Date();
		return new SeckillResult<Long>(true, date.getTime()); 
	}
}
