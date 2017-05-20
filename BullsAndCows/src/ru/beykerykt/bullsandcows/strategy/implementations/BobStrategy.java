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
package ru.beykerykt.bullsandcows.strategy.implementations;

import java.util.ArrayList;
import java.util.List;

import ru.beykerykt.bullsandcows.GameUtils;

public class BobStrategy extends RandomStrategy {

	private String next;

	@Override
	public void reset() {
		super.reset();
		next = null;
	}

	@Override
	public String getGuessCode() {
		lastCode = getG();
		return lastCode;
	}

	@Override
	public void onReceivingResponse(String response) {
		// response - R(i) (0:0)
		super.onReceivingResponse(response);
		if (response.equals("WON")) {
			return;
		}

		List<String> list = new ArrayList<String>(allCodes); // теневой лист
		for (String possible : list) {
			if (!GameUtils.getHint(lastCode, possible).equals(response)) {
				if (allCodes.contains(possible)) {
					allCodes.remove(possible);
				}
			}
		}

		if (allCodes.size() > 0) {
			list = new ArrayList<String>(allCodes);
			next = getLow(list);
		} else {
			next = null;
		}
	}

	public String getLow(List<String> list) {
		// int bulls = Integer.parseInt(response.split(":")[0]);
		// int cows = Integer.parseInt(response.split(":")[1]);
		String low = lastCode;

		List<String> lowGuesses = new ArrayList<String>();

		int minBulls = Integer.MAX_VALUE;
		int minCows = Integer.MAX_VALUE;

		for (String possible : list) {
			String resp = GameUtils.getHint(lastCode, possible);
			if (resp.equals("WON")) {
				continue;
			}
			int tempBulls = Integer.parseInt(resp.split(":")[0]);
			int tempCows = Integer.parseInt(resp.split(":")[1]);

			if (tempBulls < minBulls) {
				minBulls = tempBulls;
				lowGuesses.clear();
				lowGuesses.add(possible);
			} else if (tempBulls == minBulls) {
				if (tempCows > 0 && tempCows < minCows) {
					minCows = tempCows;
					lowGuesses.clear();
					lowGuesses.add(possible);
				} else if (tempCows == minCows) {
					if (!lowGuesses.contains(possible)) {
						lowGuesses.add(possible);
					}
				}
			}
		}
		if (lowGuesses.size() > 1) {
			low = getRandomValue(lowGuesses);
		} else if (lowGuesses.size() == 1) {
			low = lowGuesses.get(0);
		}
		lowGuesses.clear();
		return low;
	}

	public String getG() {
		if (next == null) {
			return getDoubleG();
		}
		return next;
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
		return list.get(index);
	}
}
