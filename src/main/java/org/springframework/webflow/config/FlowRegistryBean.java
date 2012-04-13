package org.springframework.webflow.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.engine.builder.DefaultFlowHolder;
import org.springframework.webflow.engine.builder.FlowAssembler;
import org.springframework.webflow.engine.builder.FlowBuilderContext;
import org.springframework.webflow.engine.builder.model.FlowModelFlowBuilder;
import org.springframework.webflow.engine.builder.support.FlowBuilderContextImpl;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.engine.model.builder.DefaultFlowModelHolder;


@Component
public class FlowRegistryBean implements InitializingBean {

	@Autowired
	private FlowRegistryFactoryBean flowRegistryFactoryBean;
	
	@Autowired
	private FlowBuilderServices flowBuilderServices;
	
	@Autowired
	private SimpleFlowBuilder simpleFlowBuilder;

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(getClass().getName() + " after properties set");
		
		DefaultFlowRegistry flowRegistry = (DefaultFlowRegistry) flowRegistryFactoryBean.getObject();
		
		DefaultFlowModelHolder flowModelHolder = new DefaultFlowModelHolder(simpleFlowBuilder);
		
		flowRegistry.getFlowModelRegistry().registerFlowModel("main", flowModelHolder);
		
		FlowModelFlowBuilder builder = new FlowModelFlowBuilder(flowModelHolder);
		
		FlowBuilderContext builderContext = new FlowBuilderContextImpl("main", new LocalAttributeMap(), flowRegistry, flowBuilderServices);
		FlowAssembler assembler = new FlowAssembler(builder, builderContext);
		DefaultFlowHolder defaultFlowHolder = new DefaultFlowHolder(assembler);
		
		flowRegistry.registerFlowDefinition(defaultFlowHolder);
	}
}
