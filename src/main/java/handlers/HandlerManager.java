package handlers;

import dao.DaoManager;

public class HandlerManager {
	LoginHandler loginHandler;
	DaoManager daoManager;
	
	public HandlerManager(DaoManager daoManager) {
		loginHandler = new LoginHandler(daoManager);
		this.daoManager = daoManager;
	}
}
