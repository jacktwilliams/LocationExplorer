package handlers;

import dao.DaoManager;

public class BaseHandler {
	protected DaoManager daoManager;
	
	public BaseHandler(DaoManager daoManager) {
		this.daoManager = daoManager;
	}
}
