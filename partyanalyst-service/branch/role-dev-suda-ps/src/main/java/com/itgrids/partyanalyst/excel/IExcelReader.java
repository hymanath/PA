package com.itgrids.partyanalyst.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface IExcelReader {
	public void readCSV(File filePath) throws CsvException;
	public List<ConstituencyBlock> getConstituencyBlocks();
	
}
