/**
* MIT License
* 
* Copyright (c) 2017 Vladimir Mikhaylov
* 
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
* 
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
**/
package ru.beykerykt.bullsandcows.base.players.bot.finder.bob;

import java.util.ArrayList;
import java.util.List;

import ru.beykerykt.bullsandcows.base.players.bot.finder.RandomFinder;
import ru.beykerykt.bullsandcows.base.utils.GameUtils;

public class BobFinder extends RandomFinder {

	private String possibleCode; // G(i)
	private List<String> possibleCodes; // P(i)

	@Override
	public void reset() {
		super.reset();
		if (possibleCodes != null) {
			possibleCodes.clear();
		}
		possibleCodes = new ArrayList<String>();
	}

	@Override
	public String getGuessCode() {
		lastCode = getG(GameUtils.CODE_POWER_LENGTH);
		return lastCode;
	}

	@Override
	public void onReceivingResponse(String response) {
		// response - R(i)
		if (response.equals("0:0")) {
			List<String> P = GameUtils.getP(lastCode);
			for (String s : P) {
				if (allCodes.contains(s)) {
					allCodes.remove(s);
				}
			}
			return;
		} else {

		}

		if (allCodes.contains(lastCode)) {
			allCodes.remove(lastCode);
		}
	}

	private boolean triggered;

	public String getG(int length) {
		if (!possibleCodes.isEmpty()) {
			return possibleCodes.get(0);
		}

		if (!triggered) {
			return getDoubleG(); // get random double g
		} else {
			return allCodes.get(0);
		}
	}

	private String getDoubleG() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < GameUtils.CODE_POWER_LENGTH; i++) {
			for (String code : allCodes) {
				if (code.contains(String.valueOf(i) + String.valueOf(i))) {
					list.add(code);
				}
			}
		}
		return getRandomValue(list);
	}

	private String getRandomValue(List<String> list) {
		int index = rand.nextInt(list.size());
		System.out.println(list.get(index));
		return list.get(index);
	}
}
