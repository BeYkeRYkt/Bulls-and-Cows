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
package ru.beykerykt.bullsandcows.base.players.bot.finder;

import java.util.Arrays;

public class BobFinder implements CodeFinder {

	private int CODE_POWER[] = { 0, 1, 2, 3, 4, 5, 6 };
	private int CODE_POWER_LENGTH = CODE_POWER.length - 1;
	private int CODE_LENGTH = 4;
	private int MAX_CODE_LENGTH = CODE_POWER_LENGTH * CODE_POWER_LENGTH * CODE_POWER_LENGTH * CODE_POWER_LENGTH;
	private char[] GUESS_CODES = new char[CODE_LENGTH + 1];

	@Override
	public int getGuessCode() {

		return 0;
	}

	@Override
	public void reset() {

	}

}
