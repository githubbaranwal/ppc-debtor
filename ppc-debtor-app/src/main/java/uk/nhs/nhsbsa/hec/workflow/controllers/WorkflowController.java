package uk.nhs.nhsbsa.hec.workflow.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.nhs.nhsbsa.hec.workflow.dto.WorkItemDto;
import uk.nhs.nhsbsa.hec.workflow.service.IStatusService;
import uk.nhs.nhsbsa.hec.workflow.service.IWorkflowService;
import uk.nhs.nhsbsa.hec.workflow.types.DirectDebitDebtorStatus;
import uk.nhs.nhsbsa.hec.workflow.util.WorkflowUtil;

@Controller
public class WorkflowController {

	private static final String VIEW_ATTRIBUTE_ITEMTITLES = "workitemTitle";

	private static final String VIEW_ATTRIBUTE_ITEMS = "workitems";
	
	private static final String PAGE_HOME = "index";
	private static final String PAGE_REDIRECT = "redirect";
	private static final String PAGE_WORKITEM = "workitem";
	private static final String PAGE_WORKITEMS_LIST = "workitems";

	private static final Logger logger = LoggerFactory.getLogger(WorkflowController.class);

	@Autowired
	private IWorkflowService workflowService;
	
	@Autowired
	private IStatusService statusService;
	
	@ExceptionHandler(Exception.class)
	public String generalException(Exception e) {
		logger.error("Error during handling request", e);
		return "error";
	}
	
	@RequestMapping(path = "/")
	public String rootRedirectHome() {
		return PAGE_HOME;
	}

	@RequestMapping(path = "/new", method = RequestMethod.GET)
	public String displayNewItem() {
		return "newDebtor";
	}

	@RequestMapping(path = "/add", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
	public ModelAndView addItemPost(@ModelAttribute("workitem") WorkItemDto workItemDto, HttpServletRequest request) {

		List<WorkItemDto> workItems = workflowService.getByCertNumber(workItemDto.getReference());
		
		if( workItems.isEmpty() )
		{
			return getRedirectModelView(workflowService.addWorkItem(workItemDto, getUser(request)));
		}
		else
		{
			return getRedirectModelView(workItems.get(0));
		}
	}

	private ModelAndView getRedirectModelView(WorkItemDto workItem) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(PAGE_REDIRECT);
		mv.addObject("redirectUrl", String.format("/item/%d", workItem.getId()));
		return mv;
	}

	@RequestMapping(path = "/{workstreamId}/items", method = RequestMethod.GET)
	public ModelAndView displayWorkItemsByWorkstream(@PathVariable int workstreamId) {
		if (logger.isDebugEnabled())
			logger.debug("display items for workstream: {}", workstreamId);

		List<WorkItemDto> workItems = workflowService.getAll(workstreamId, 0);
		String workstreamTitle = "ALL";

		ModelAndView mv = new ModelAndView();
		mv.addObject(VIEW_ATTRIBUTE_ITEMS, workItems);
		mv.addObject(VIEW_ATTRIBUTE_ITEMTITLES, workstreamTitle);
		mv.setViewName(PAGE_WORKITEMS_LIST);
		return mv;
	}

	@RequestMapping(path = "/{workstreamId}/{workItemStatus}/escalated", method = RequestMethod.GET)
	public ModelAndView displayEscalatedWorkItems(@PathVariable int workstreamId, @PathVariable int workItemStatus) {
		if (logger.isDebugEnabled())
			logger.debug("display escalated items for workstream: {}", workstreamId);

		DirectDebitDebtorStatus status = DirectDebitDebtorStatus.getStatus(workItemStatus);

		List<WorkItemDto> workItems = workflowService.getAllEscalated(status, 0);
		String workstreamTitle = String.format(status.getStatusText());

		ModelAndView mv = new ModelAndView();
		mv.addObject(VIEW_ATTRIBUTE_ITEMS, workItems);
		mv.addObject(VIEW_ATTRIBUTE_ITEMTITLES, workstreamTitle);
		mv.setViewName(PAGE_WORKITEMS_LIST);
		return mv;
	}

	@RequestMapping("/item/{workItemId}")
	public ModelAndView displayWorkitem(@PathVariable int workItemId, HttpServletRequest request) {
		WorkItemDto workItem = workflowService.getById(workItemId);
		
		return setModelViewForWorkItem(workItem);
	}

	/**
	 * Set model for the work item view
	 * 
	 * @param workItem
	 * @return
	 */
	private ModelAndView setModelViewForWorkItem(WorkItemDto workItem)
	{
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("workitemStatuses",
				statusService.getNextStatuses(workItem.getWorkItemStatus(), workItem));
		modelView.addObject("workitem", workItem);
		modelView.setViewName(PAGE_WORKITEM);
		
		return modelView;
	}
	
	@RequestMapping("/search")
	public ModelAndView search(@ModelAttribute("workitem") WorkItemDto workItemDto) 
	{
		List<WorkItemDto> workItems = workflowService.getByCertNumber(workItemDto.getReference());

		ModelAndView mv = new ModelAndView();

		String workstreamTitle = "Search Result: " + workItemDto.getReference();

		mv.addObject(VIEW_ATTRIBUTE_ITEMS, workItems);
		mv.addObject(VIEW_ATTRIBUTE_ITEMTITLES, workstreamTitle);
		mv.setViewName(PAGE_WORKITEMS_LIST);

		return mv;
	}

	@RequestMapping(path = "/item/update", method = RequestMethod.POST)
	public ModelAndView updateWorkitem(@ModelAttribute("workitem") WorkItemDto workitemUpdate, HttpServletRequest request) {
		WorkItemDto workItemReturn = null;
		
		workItemReturn = workflowService.updateWorkItem(workitemUpdate, getUser(request));

		return setModelViewForWorkItem(workItemReturn);
	}
	
	private String getUser(HttpServletRequest request){
		String user;
		if(request.getUserPrincipal() == null){
			user = "NOUSER";	
		}
		else{
			user = request.getUserPrincipal().getName().toUpperCase();
		}
		return user;
	}
}
