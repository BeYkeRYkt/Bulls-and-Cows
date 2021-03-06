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
package ru.beykerykt.bullsandcows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class GameUtils {

	private static ScheduledExecutorService es = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors() * 2);
	public static int CODE_POWER[] = { 0, 1, 2, 3, 4, 5, 6 };
	public static int CODE_POWER_LENGTH = CODE_POWER.length;
	public static int CODE_LENGTH = 4;
	public static int ADDITIONAL_CORES = 0;

	private static boolean shutdown;

	public static String generateRandomCode() {
		Random rand = new Random();
		List<String> allCodes = getAllCodes(CODE_POWER_LENGTH);
		return allCodes.get(rand.nextInt(allCodes.size()));
	}

	public static List<String> getAllCodes(int length) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				for (int k = 0; k < length; k++) {
					for (int l = 0; l < length; l++) {
						String code = String.valueOf(i) + String.valueOf(j) + String.valueOf(k) + String.valueOf(l);
						list.add(code);
					}
				}
			}
		}
		return list;
	}

	public static boolean isExecutorShutdown() {
		return shutdown;
	}

	public static void shutdownExecutor(boolean flag) {
		shutdown = flag;
		if (flag) {
			getExecutorService().shutdownNow();
		}
	}

	public static ScheduledExecutorService getExecutorService() {
		if ((es == null || es.isShutdown()) && !isExecutorShutdown()) {
			es = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors() + ADDITIONAL_CORES);
		}
		return es;
	}

	public static String getHint(String secret, String guess) {
		int s = 0;
		int g = 0;

		int[] num = new int[CODE_POWER_LENGTH];
		int bulls = 0;
		int cows = 0;
		for (int k = 0; k < secret.length(); k++) {
			s = secret.charAt(k) - 48;
			g = guess.charAt(k) - 48;
			if (s == g) {
				bulls++;
			} else {
				if (num[s] < 0) {
					cows++;
				}
				if (num[g] > 0) {
					cows++;
				}
				num[s]++;
				num[g]--;
			}
		}
		if (bulls == 4) {
			return "WON";
		}
		return bulls + ":" + cows;
	}
}