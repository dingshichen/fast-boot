package cn.dsc.sleuth.config.impl;

import brave.Tracing;
import brave.propagation.TraceContext;
import cn.dsc.sleuth.config.TraceService;
import lombok.RequiredArgsConstructor;

/**
 * @author dingShiChen
 * @since 2019/8/28
 */
@RequiredArgsConstructor
public class DefaultTraceServiceImpl implements TraceService {

	private final Tracing tracing;

	@Override
	public String trace() {
		return traceContext().traceIdString();
	}

	@Override
	public String span() {
		return traceContext().spanIdString();
	}

	@Override
	public String traceWithSpan() {
		return traceContext().toString();
	}

	private TraceContext traceContext(){
		return this.tracing.currentTraceContext().get();
	}
}
