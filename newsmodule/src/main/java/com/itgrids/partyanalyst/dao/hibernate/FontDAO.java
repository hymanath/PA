package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IFontDAO;
import com.itgrids.partyanalyst.model.Font;

public class FontDAO  extends GenericDaoHibernate<Font,Integer> implements IFontDAO {

	public FontDAO(){
		super(Font.class);
	}
}
