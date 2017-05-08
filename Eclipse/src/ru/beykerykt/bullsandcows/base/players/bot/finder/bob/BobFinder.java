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

import java.util.Arrays;

import ru.beykerykt.bullsandcows.base.players.bot.finder.RandomFinder;
import ru.beykerykt.bullsandcows.base.utils.GameUtils;

public class BobFinder extends RandomFinder {

	@Override
	public String getGuessCode() {
		lastCode = getG(GameUtils.CODE_POWER_LENGTH);
		return lastCode;
	}

	@Override
	public void onReceivingResponse(String response) {
		if (response.equals("0:0")) {
			if (allCodes.contains(lastCode)) {
				allCodes.remove(lastCode);
			}
		}
		System.out.println(response);
		System.out.println("Size: " + allCodes.size());
		System.out.println(allCodes.get(0).length());
		String[] a = lastCode.split("");
		showPermutations(a);
	}

	static void showPermutations(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length - 1; j++) {
				System.out.println(Arrays.toString(arr));
				String tmp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = tmp;
			}
		}
	}

	public String getG(int length) {
		int randInt = rand.nextInt(length);
		for (String code : allCodes) {
			if (code.contains(String.valueOf(randInt) + String.valueOf(randInt))) {
				return code;
			}
		}
		return lastCode;
	}

}
