package com.itgrids.partyanalyst.strategy;

@Deprecated
public interface IIntegrationTemplate<T, U> {

	U execute(T t);
}
