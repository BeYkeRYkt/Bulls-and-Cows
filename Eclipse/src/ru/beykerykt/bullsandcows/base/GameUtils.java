/**
* The MIT License (MIT)
*
* Copyright (c) 2017 D3v3l0p3d_0n3
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
*/
package ru.beykerykt.bullsandcows.base;

import java.util.Random;

public class GameUtils {

	public static String generateRandomCode() {
		Random rand = new Random();
		int target = 0;
		while (hasDupes(target = (rand.nextInt(9000) + 1000)))
			;
		String targetStr = String.valueOf(target);
		return targetStr;
	}

	public static boolean hasDupes(int num) {
		boolean[] digs = new boolean[10];
		while (num > 0) {
			if (digs[num % 10])
				return true;
			digs[num % 10] = true;
			num /= 10;
		}
		return false;
	}
	
	
}