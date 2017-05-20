package ru.beykerykt.bullsandcows.strategy.implementations;

import ru.beykerykt.bullsandcows.strategy.IStrategy;

public class MagicStrategy implements IStrategy{

	private String code;
	
	public MagicStrategy(String code) {
		this.code = code;
	}
	
	@Override
	public void reset() {
		//this.code = null;
	}

	@Override
	public String getGuessCode() {
		return code;
	}

	@Override
	public void onReceivingResponse(String response) {

	}

}
