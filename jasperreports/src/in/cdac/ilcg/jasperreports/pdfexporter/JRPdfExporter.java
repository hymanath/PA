/*

 *
 * C-DAC,
 * #68, Electronic City,
 * Bangalore, 560100
 * http://www.cdacbangalore.in
 */

/*
 * Contributors:
 * RKVS Raman - raman@cdacbangalore.in
 * Laxminarayana.A - laxman@cdacbangalore.in
 */


package in.cdac.ilcg.jasperreports.pdfexporter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.AttributedCharacterIterator;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRAlignment;
import net.sf.jasperreports.engine.JRAnchor;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.JRHyperlink;
import net.sf.jasperreports.engine.JRImage;
import net.sf.jasperreports.engine.JRImageRenderer;
import net.sf.jasperreports.engine.JRLine;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.JRPrintAnchor;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintEllipse;
import net.sf.jasperreports.engine.JRPrintFrame;
import net.sf.jasperreports.engine.JRPrintHyperlink;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.engine.JRPrintLine;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JRPrintRectangle;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.JRRenderable;
import net.sf.jasperreports.engine.JRReportFont;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JRTextElement;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.base.JRBaseFont;
import net.sf.jasperreports.engine.export.legacy.BorderOffset;
import net.sf.jasperreports.engine.fonts.FontFace;
import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.fonts.FontInfo;
import net.sf.jasperreports.engine.util.BreakIteratorSplitCharacter;
import net.sf.jasperreports.engine.util.JRFontUtil;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.engine.util.JRStyledText;
import net.sf.jasperreports.engine.util.JRTextAttribute;


//------------------------------------------------------
//import java.net.URL;
import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;

import ooo.connector.BootstrapSocketConnector;
import com.sun.star.beans.PropertyValue;
//import com.sun.star.comp.helper.Bootstrap;
import com.sun.star.frame.XComponentLoader;
import com.sun.star.frame.XStorable;
//import com.sun.star.io.XInputStream;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import com.sun.star.util.XCloseable;
//import java.io.BufferedInputStream;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.FileInputStream;
//import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.export.FontKey;
import net.sf.jasperreports.engine.export.JRExportProgressMonitor;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
//import net.sf.jasperreports.engine.export.JRPdfExporterTagHelper;
import net.sf.jasperreports.engine.export.PdfFont;
//import net.sf.jasperreports.engine.export.JRPdfExporterTagHelper;

//------------------------------------------------------

/**
 * Exports a JasperReports document to PDF format. It has binary output type and exports the document to
 * a free-form layout.
 * <p>
 * Since OpenOffice is capable to converting ODT file into PDF, initially an temporary intermediate ODT file is created
 * and that will be used to generated a PDF file using OpenOffice library.
 * Since classic AWT fonts can be sometimes very different from PDF fonts, a font mapping feature was added.
 * By using the {@link JRExporterParameter#FONT_MAP} parameter, a logical font like "sansserif" can be mapped
 * to a system specific font, like "Helvetica-BoldOblique". PDF font mapping is a little more complicated, because
 * for a logical font, PDF cand provide two or more fonts, from the same family but with different styles (like
 * "Helvetica", "Helvetica-Bold", "Helvetica-BoldOblique"). So every key in the map is a simple bean containing
 * font family, bold and italic flag, and every value is another bean containing the PDF font name, encoding and
 * embedding flag.
 * @see FontKey
 * @see PdfFont
 * @author
 * RKVS Raman (raman@cdacbangalore.in)
 * Laxminarayana.A (laxman@cdacbangalore.in)
 */
public class JRPdfExporter extends JRAbstractExporter
{

	/**
	 * @deprecated Replaced by {@link JRPdfExporterParameter#PROPERTY_FORCE_SVG_SHAPES}.
	 */
	public static final String PDF_FORCE_SVG_SHAPES = JRPdfExporterParameter.PROPERTY_FORCE_SVG_SHAPES;

	public static final String PDF_EXPORTER_PROPERTIES_PREFIX = JRProperties.PROPERTY_PREFIX + "export.pdf.";
	//private static final String EMPTY_BOOKMARK_TITLE = "";

    /**
	 * The exporter key, as used in
	 * {@link GenericElementHandlerEnviroment#getHandler(net.sf.jasperreports.engine.JRGenericElementType, String)}.
	 */
	public static final String PDF_EXPORTER_KEY = JRProperties.PROPERTY_PREFIX + "pdf";

	private static final String EMPTY_BOOKMARK_TITLE = "";

	/**
	 *
	 */
	protected static final String JR_PAGE_ANCHOR_PREFIX = "JR_PAGE_ANCHOR_";

	protected static boolean fontsRegistered = false;

	/**
	 *
	 */
//	protected Document document = null;
//	protected PdfContentByte pdfContentByte = null;
//	protected PdfWriter pdfWriter = null;
//
//	protected Document imageTesterDocument = null;
//	protected PdfContentByte imageTesterPdfContentByte = null;

	//protected JRPdfExporterTagHelper tagHelper = new JRPdfExporterTagHelper(this);

	protected JRExportProgressMonitor progressMonitor = null;

	protected int reportIndex = 0;

	/**
	 *
	 */
	protected boolean forceSvgShapes;
	protected boolean isCreatingBatchModeBookmarks;
	protected boolean isCompressed;
	protected boolean isEncrypted;
	protected boolean is128BitKey;
	protected String userPassword;
	protected String ownerPassword;
	protected int permissions = 0;
	protected Character pdfVersion;
	protected String pdfJavaScript;
    protected String printScaling;

    protected boolean isTagged;
    PropertyValue pdfFilterData[] = new PropertyValue[30];

	/**
	 *
	 */
	protected Map loadedImagesMap = null;
//	protected Image pxImage = null;

	//private BookmarkStack bookmarkStack = null;

	private Map fontMap = null;


	
	/**
	 *
	 */
	public void exportReport() throws JRException
	{
//		registerFonts();

		progressMonitor = (JRExportProgressMonitor)parameters.get(JRExporterParameter.PROGRESS_MONITOR);

		/*   */
		setOffset();

		try
		{
			/*   */
			setExportContext();

			/*   */
			setInput();

			if (!parameters.containsKey(JRExporterParameter.FILTER))
			{
				filter = createFilter(PDF_EXPORTER_PROPERTIES_PREFIX);
			}

			/*   */
			if (!isModeBatch)
			{
				setPageRange();
			}

			isCreatingBatchModeBookmarks =
				getBooleanParameter(
					JRPdfExporterParameter.IS_CREATING_BATCH_MODE_BOOKMARKS,
					JRPdfExporterParameter.PROPERTY_CREATE_BATCH_MODE_BOOKMARKS,
					false
					);

			forceSvgShapes = //FIXME certain properties need to be read from each individual document in batch mode; check all exporters and all props
				getBooleanParameter(
					JRPdfExporterParameter.FORCE_SVG_SHAPES,
					JRPdfExporterParameter.PROPERTY_FORCE_SVG_SHAPES,
					false
					);

			isCompressed =
				getBooleanParameter(
					JRPdfExporterParameter.IS_COMPRESSED,
					JRPdfExporterParameter.PROPERTY_COMPRESSED,
					false
					);
            pdfFilterData[0] = new PropertyValue();
            pdfFilterData[0].Name = "UseLosslessCompression";
            pdfFilterData[0].Value = new Boolean(isCompressed);

			isEncrypted =
				getBooleanParameter(
					JRPdfExporterParameter.IS_ENCRYPTED,
					JRPdfExporterParameter.PROPERTY_ENCRYPTED,
					false
					);
            pdfFilterData[1] = new PropertyValue();
	        pdfFilterData[1].Name = "EncryptFile";
	        pdfFilterData[1].Value = new Boolean(isEncrypted);

			is128BitKey =
				getBooleanParameter(
					JRPdfExporterParameter.IS_128_BIT_KEY,
					JRPdfExporterParameter.PROPERTY_128_BIT_KEY,
					false
					);

			userPassword =
				getStringParameter(
					JRPdfExporterParameter.USER_PASSWORD,
					JRPdfExporterParameter.PROPERTY_USER_PASSWORD
					);
            pdfFilterData[2] = new PropertyValue();
            pdfFilterData[2].Name = "DocumentOpenPassword";
            pdfFilterData[2].Value = userPassword;

            if(userPassword != null)
            {
                pdfFilterData[3] = new PropertyValue();
                pdfFilterData[3].Name = "EncryptFile";
                pdfFilterData[3].Value = new Boolean(true);
            }

			ownerPassword =
				getStringParameter(
					JRPdfExporterParameter.OWNER_PASSWORD,
					JRPdfExporterParameter.PROPERTY_OWNER_PASSWORD
					);

			Integer permissionsParameter = (Integer)parameters.get(JRPdfExporterParameter.PERMISSIONS);
			if (permissionsParameter != null)
			{
				permissions = permissionsParameter.intValue();
                pdfFilterData[4] = new PropertyValue();
                pdfFilterData[4].Name = "Changes";
                pdfFilterData[4].Value = new Integer(permissions);
			}

			pdfVersion =
				getCharacterParameter(
						JRPdfExporterParameter.PDF_VERSION,
						JRPdfExporterParameter.PROPERTY_PDF_VERSION
						);
            pdfFilterData[5] = new PropertyValue();
            pdfFilterData[5].Name = "SelectPdfVersion";
            pdfFilterData[5].Value = pdfVersion;

			fontMap = (Map) parameters.get(JRExporterParameter.FONT_MAP);

//			setSplitCharacter();
			setHyperlinkProducerFactory();

			pdfJavaScript =
				getStringParameter(
					JRPdfExporterParameter.PDF_JAVASCRIPT,
					JRPdfExporterParameter.PROPERTY_PDF_JAVASCRIPT
					);

            printScaling =
				getStringParameter(
					JRPdfExporterParameter.PRINT_SCALING,
					JRPdfExporterParameter.PROPERTY_PRINT_SCALING
					);

			/*tagHelper.setTagged(
				getBooleanParameter(
					JRPdfExporterParameter.IS_TAGGED,
					JRPdfExporterParameter.PROPERTY_TAGGED,
					false
					)
				);*/
            isTagged = getBooleanParameter(
					JRPdfExporterParameter.IS_TAGGED,
					JRPdfExporterParameter.PROPERTY_TAGGED,
					false
					);
            pdfFilterData[6] = new PropertyValue();
            pdfFilterData[6].Name = "UseTaggedPDF";
            pdfFilterData[6].Value = new Boolean(isTagged);


			OutputStream os = (OutputStream)parameters.get(JRExporterParameter.OUTPUT_STREAM);
			if (os != null)
			{
                try {
                    exportReportToByteStream(os);
                } catch (IOException ex) {
                    Logger.getLogger(JRPdfExporter.class.getName()).log(Level.SEVERE, null, ex);
                }
			}
			else
			{
				File destFile = (File)parameters.get(JRExporterParameter.OUTPUT_FILE);
				if (destFile == null)
				{
					String fileName = (String)parameters.get(JRExporterParameter.OUTPUT_FILE_NAME);
					if (fileName != null)
					{
						destFile = new File(fileName);
					}
					else
					{
						throw new JRException("No output specified for the exporter.");
					}
				}

				try
				{
					os = new FileOutputStream(destFile);
					exportReportToStream(os);
					os.flush();
				}
				catch (IOException e)
				{
					throw new JRException("Error trying to export to file : " + destFile, e);
				}
				finally
				{
					if (os != null)
					{
						try
						{
							os.close();
						}
						catch(IOException e)
						{
						}
					}
				}
			}
		}
		finally
		{
			resetExportContext();
		}
	}


	/**
	 *
	 */

    protected void exportReportToStream(OutputStream os) throws JRException, IOException
	{
		//ByteArrayOutputStream baos = new ByteArrayOutputStream();

        //String destFileName = parameters.get(JRPdfExporterParameter.OUTPUT_FILE).toString();
        String destFileName = null;
        File dest_File =(File)parameters.get(JRPdfExporterParameter.OUTPUT_FILE);
        if(parameters.get(JRPdfExporterParameter.OUTPUT_FILE_NAME)!= null)
        {
            destFileName = parameters.get(JRPdfExporterParameter.OUTPUT_FILE_NAME).toString();
        }
        else if(dest_File !=null && destFileName ==null)
        {
            destFileName = dest_File.toString();
        }
        //String jasperPrint = (String)parameters.get(JRPdfExporterParameter.JASPER_PRINT);
        long start = System.currentTimeMillis();

            File destFile;
                destFile = File.createTempFile(jasperPrint.getName(),".odt",null);

		try{

			JROdtExporter exporter = new JROdtExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
			//exporter.setParameter(JRCsvExporterParameter.FIELD_DELIMITER, "|");

			exporter.exportReport();

			System.err.println("ODT creation time : " + (System.currentTimeMillis() - start));
		}catch (JRException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		//--------------------------------------------------------

		//--------------------------pdf------------------------------
		
        String loadUrl=destFile.toURL().toString();
        String subLoadStart = "file://";
        String subLoadEnd = loadUrl.substring(loadUrl.indexOf((int)'/'));
        loadUrl = subLoadStart.concat(subLoadEnd);

        File store_file = new File(destFileName);
        String storeUrl = store_file.toURL().toString();
        String subStoreStart = "file://";
        String subStoreEnd = storeUrl.substring(storeUrl.indexOf((int)'/'));
        storeUrl = subStoreStart.concat(subStoreEnd);

		try {
                    String oooExeFolder;
                    if(System.getenv("OPENOFFICE_PATH")==null)
                    {
                        oooExeFolder = System.getProperty("OPENOFFICE_PATH");
                    }
                    else
                    {
                        oooExeFolder = System.getenv("OPENOFFICE_PATH");//"/usr/lib/openoffice/program/";
                    }
					System.out.println("oooExeFolder" + oooExeFolder);
                    //String oooExeFolder = System.getenv("OPENOFFICE_PATH");//"/usr/lib/openoffice/program/";
                    XComponentContext xContext = BootstrapSocketConnector.bootstrap(oooExeFolder);
                    //--------------
	                XMultiComponentFactory xMultiComponentFactory = xContext.getServiceManager();
        	        XComponentLoader xcomponentloader = (XComponentLoader) UnoRuntime.queryInterface(XComponentLoader.class,xMultiComponentFactory.createInstanceWithContext("com.sun.star.frame.Desktop", xContext));
                	PropertyValue[] props = new PropertyValue[1];
	                props[0] = new PropertyValue();
        	        props[0].Name = "Hidden";
                	props[0].Value = new Boolean(true);
	                			//Object objectDocumentToStore = xcomponentloader.loadComponentFromURL(loadUrl, "_blank", 0, props);
						Object objectDocumentToStore = xcomponentloader.loadComponentFromURL(destFile.getAbsolutePath(), "_blank", 0, props);


	                PropertyValue[] conversionProperties = new PropertyValue[3];
	                conversionProperties[0] = new PropertyValue();
	                conversionProperties[0].Name = "FilterName";
	                conversionProperties[0].Value = "writer_pdf_Export";
	                conversionProperties[1] = new PropertyValue();
	                conversionProperties[1].Name = "Overwrite ";
	                conversionProperties[1].Value = new Boolean(true);
                    conversionProperties[2] = new PropertyValue();
                    conversionProperties[2].Name = "FilterData";
                    conversionProperties[2].Value = pdfFilterData;

	                XStorable xstorable = (XStorable) UnoRuntime.queryInterface(XStorable.class,objectDocumentToStore);
	                			//xstorable.storeToURL(storeUrl,conversionProperties);
						xstorable.storeToURL(store_file.getAbsolutePath(),conversionProperties);
			//return destPdfFileName;

            os.close();
            destFile.deleteOnExit();
            }
            /*catch (java.lang.Exception e) {
                e.printStackTrace();
            }
            catch (JRException e)
            {
			e.printStackTrace();
            }*/
            catch (Exception e)
            {
			e.printStackTrace();
            }
            finally {
                System.exit(0);
            }

	}
    protected void exportReportToByteStream(OutputStream os) throws JRException, IOException
	{

		long start = System.currentTimeMillis();
            File destFile;
                destFile = File.createTempFile(jasperPrint.getName(),".odt",null);
				System.out.println(destFile);

		try{

			JROdtExporter exporter = new JROdtExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
			//exporter.setParameter(JRCsvExporterParameter.FIELD_DELIMITER, "|");

			exporter.exportReport();

            OOoOutputStream outputStream = new OOoOutputStream();

            String filterName = "writer_pdf_Export";

            //String oooExeFolder = System.getenv("OPENOFFICE_PATH");//"/usr/lib/openoffice/program/";
            String oooExeFolder;
            if(System.getenv("OPENOFFICE_PATH")==null)
            {
                    oooExeFolder = System.getProperty("OPENOFFICE_PATH");
            }
            else
            {
                    oooExeFolder = System.getenv("OPENOFFICE_PATH");//"/usr/lib/openoffice/program/";
            }

            XComponentContext xComponentContext = BootstrapSocketConnector.bootstrap(oooExeFolder);


            XMultiComponentFactory xMultiComponentFactory = xComponentContext.getServiceManager();
            
            XComponentLoader xComponentLoader = (XComponentLoader) UnoRuntime.queryInterface(XComponentLoader.class, xMultiComponentFactory.createInstanceWithContext("com.sun.star.frame.Desktop", xComponentContext));


            PropertyValue[] props = new PropertyValue[1];
	        props[0] = new PropertyValue();
        	props[0].Name = "Hidden";
            props[0].Value = new Boolean(true);
	        //Object document = xComponentLoader.loadComponentFromURL("file://"+ destFile.getAbsolutePath(), "_blank", 0, props);
            XComponent objectDocument = xComponentLoader.loadComponentFromURL("file:///"+destFile.getAbsolutePath(), "_blank", 0, props);


            PropertyValue[] conversionProperties = new PropertyValue[3];
            conversionProperties[0] = new PropertyValue();
            conversionProperties[1] = new PropertyValue();
            conversionProperties[2] = new PropertyValue();


            conversionProperties[0].Name = "OutputStream";
            conversionProperties[0].Value = outputStream;//output;
            //conversionProperties[0].Value = os;//output;
            conversionProperties[1].Name = "FilterName";
            conversionProperties[1].Value = filterName;//filterName;
            conversionProperties[2].Name = "FilterData";
            conversionProperties[2].Value = pdfFilterData;//filterName;


            XStorable xstorable = (XStorable) UnoRuntime.queryInterface(XStorable.class,objectDocument);
            xstorable.storeToURL("private:stream", conversionProperties);

            XCloseable xclosable = (XCloseable) UnoRuntime.queryInterface(XCloseable.class,objectDocument);
            xclosable.close(true);

            /*byte[] byteBuff = new byte[4096];
            byteBuff =outputStream.toByteArray();

            for(int i=0;i<byteBuff.length;i++)
                System.out.println("bytebuff conversion:"+byteBuff[i]);*/

            os.write(outputStream.toByteArray());
            os.flush();
            os.close();
            destFile.deleteOnExit();
            //System.exit(0);
            //----------------------------------------------
		}catch (JRException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		//return os.toByteArray();
	}



	protected Map getDefaultPdfFontAttributes() {
		Map attrs;
		JRStyle style = jasperPrint.getDefaultStyle();
		if (style != null) {
			attrs = new HashMap();
			attrs.put(JRTextAttribute.PDF_FONT_NAME, style.getPdfFontName());
			attrs.put(JRTextAttribute.PDF_ENCODING, style.getPdfEncoding());
			attrs.put(JRTextAttribute.IS_PDF_EMBEDDED, style.isPdfEmbedded());
		} else {
			JRReportFont font = jasperPrint.getDefaultFont();
			if (font != null) {
				attrs = new HashMap();
				attrs.put(JRTextAttribute.PDF_FONT_NAME, font.getPdfFontName());
				attrs.put(JRTextAttribute.PDF_ENCODING, font.getPdfEncoding());
				attrs.put(JRTextAttribute.IS_PDF_EMBEDDED, font.isPdfEmbedded() ? Boolean.TRUE : Boolean.FALSE);
			} else {
				attrs = null;
			}
		}
		return attrs;
	}



	private float getXAlignFactor(JRPrintImage printImage)
	{
		float xalignFactor = 0f;
		switch (printImage.getHorizontalAlignment())
		{
			case JRAlignment.HORIZONTAL_ALIGN_RIGHT :
			{
				xalignFactor = 1f;
				break;
			}
			case JRAlignment.HORIZONTAL_ALIGN_CENTER :
			{
				xalignFactor = 0.5f;
				break;
			}
			case JRAlignment.HORIZONTAL_ALIGN_LEFT :
			default :
			{
				xalignFactor = 0f;
				break;
			}
		}
		return xalignFactor;
	}


	private float getYAlignFactor(JRPrintImage printImage)
	{
		float yalignFactor = 0f;
		switch (printImage.getVerticalAlignment())
		{
			case JRAlignment.VERTICAL_ALIGN_BOTTOM :
			{
				yalignFactor = 1f;
				break;
			}
			case JRAlignment.VERTICAL_ALIGN_MIDDLE :
			{
				yalignFactor = 0.5f;
				break;
			}
			case JRAlignment.VERTICAL_ALIGN_TOP :
			default :
			{
				yalignFactor = 0f;
				break;
			}
		}
		return yalignFactor;
	}

    protected String getExporterKey()
	{
		return PDF_EXPORTER_KEY;
	}


	/**
	 * Output stream implementation that discards all the data.
	 */
	public static class NullOutputStream extends OutputStream
	{
		public NullOutputStream()
		{
		}

		public void write(int b)
		{
			// discard the data
		}

		public void write(byte[] b, int off, int len)
		{
			// discard the data
		}

		public void write(byte[] b)
		{
			// discard the data
		}
	}


}
