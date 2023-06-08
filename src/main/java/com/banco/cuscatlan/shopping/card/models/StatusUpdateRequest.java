package com.banco.cuscatlan.shopping.card.models;

public class StatusUpdateRequest {
	
	private int idOrder;
	private int idStatus;
	
	public StatusUpdateRequest(int idOrder, int idStatus) {
		super();
		this.idOrder = idOrder;
		this.idStatus = idStatus;
	}

	public StatusUpdateRequest() {
		super();
	}
	
	

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public int getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	@Override
	public String toString() {
		return "StatusUpdateRequest [idOrder=" + idOrder + ", idStatus=" + idStatus + "]";
	}
	
	

}
