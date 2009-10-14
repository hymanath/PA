package com.itgrids.partyanalyst.excel;

import java.util.ArrayList;
import java.util.List;

public interface IExcelReader {
	public void readCSV(String filePath) throws CsvException;
	public List<ConstituencyBlock> getConstituencyBlocks();
	
}
