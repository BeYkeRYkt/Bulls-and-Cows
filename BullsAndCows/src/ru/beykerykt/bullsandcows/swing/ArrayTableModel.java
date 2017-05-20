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
package ru.beykerykt.bullsandcows.swing;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ArrayTableModel extends AbstractTableModel {

	/**
	 * AUTOGENERATE SERIAL UID
	 */
	private static final long serialVersionUID = 1L;
	
	private List<TableEntry> list;
	private int COLUMN_COUNT = 3;

	public ArrayTableModel(List<TableEntry> humans) {
		super();
		this.list = humans;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_COUNT;
	}

	@Override
	public String getColumnName(int c) {
		String result = "";
		switch (c) {
			case 0:
				result = "Попытка";
				break;
			case 1:
				result = "Код";
				break;
			case 2:
				result = "Ответ";
				break;
		}
		return result;
	}

	@Override
	public Object getValueAt(int r, int c) {
		switch (c) {
			case 0:
				return list.get(r).getAttempt();
			case 1:
				return list.get(r).getGuess();
			case 2:
				return list.get(r).getResponse();
			default:
				return "";
		}
	}

	public List<TableEntry> getList() {
		return list;
	}
}
