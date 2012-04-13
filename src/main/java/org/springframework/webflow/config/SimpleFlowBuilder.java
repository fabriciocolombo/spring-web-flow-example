package org.springframework.webflow.config;

import java.util.LinkedList;

import javax.servlet.ServletContext;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.webflow.engine.Flow;
import org.springframework.webflow.engine.State;
import org.springframework.webflow.engine.model.FlowModel;
import org.springframework.webflow.engine.model.ViewStateModel;
import org.springframework.webflow.engine.model.builder.FlowModelBuilder;
import org.springframework.webflow.engine.model.builder.FlowModelBuilderException;

@Component 
public class SimpleFlowBuilder implements FlowModelBuilder, ServletContextAware {

	private static ServletContext servletContext;
	
	@Override
	public void init() throws FlowModelBuilderException {

	}

	@Override
	public void build() throws FlowModelBuilderException {

	}

	@SuppressWarnings("unchecked")
	@Override
	public FlowModel getFlowModel() throws FlowModelBuilderException {
		FlowModel flowModel = new FlowModel();

		@SuppressWarnings("rawtypes")
		LinkedList states = new LinkedList();

		ViewStateModel viewStateModel = new ViewStateModel("main");

		states.add(viewStateModel);

		flowModel.setStates(states);

		return flowModel;
	}

	@Override
	public void dispose() throws FlowModelBuilderException {

	}

	@Override
	public Resource getFlowModelResource() {
		ServletContextResource resource = new ServletContextResource(servletContext, "/WEB-INF/flows/main/main-flow.xml");

		return resource;
	}

	@Override
	public boolean hasFlowModelResourceChanged() {
		return false;
	}

	class SimpleFlow extends Flow {

		public SimpleFlow(String id) {
			super(id);
		}

		@Override
		public void add(State state) throws IllegalArgumentException {
			super.add(state);
		}
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		SimpleFlowBuilder.servletContext = servletContext;		
	}

}
