package cn.dsc.sleuth.config.impl;

import brave.Tracing;
import brave.propagation.TraceContext;
import cn.dsc.sleuth.config.TraceService;

/**
 * @author dingShiChen
 * @since 2019/8/28
 */
public class DefaultTraceServiceImpl implements TraceService {

	private Tracing  tracing;

	public DefaultTraceServiceImpl(Tracing tracing) {
		this.tracing = tracing;
	}

	@Override
	public String trace() {
		TraceContext traceContext = this.tracing.currentTraceContext().get();
		return traceContext.traceIdString();
	}

	@Override
	public String span() {
		TraceContext traceContext = this.tracing.currentTraceContext().get();
		return traceContext.spanIdString();
	}
}
