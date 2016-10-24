//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.10.23 alle 10:10:37 AM CEST 
//


package generated;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per healthprofile complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="healthprofile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lastupdate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="weight" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="height" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="bmi" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "healthprofile", propOrder = {
    "lastupdate",
    "weight",
    "height",
    "bmi"
})
public class Healthprofile {

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastupdate;
    protected double weight;
    protected double height;
    protected double bmi;

    public Healthprofile(){}
    
    public Healthprofile(double w, double h, String luStr) throws ParseException, DatatypeConfigurationException {
		// TODO Auto-generated constructor stub
    	DateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
	    Date dob=df.parse(luStr);
	    
	    GregorianCalendar cal = new GregorianCalendar();
	    cal.setTime(dob);
	    XMLGregorianCalendar lu = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH), dob.getHours(),dob.getMinutes(),dob.getSeconds(),DatatypeConstants.FIELD_UNDEFINED, cal.getTimeZone().LONG).normalize();    	
	    
    	this.lastupdate = lu;
		this.weight = w;
		this.height = h;    
		this.bmi = this.weight/(Math.pow(this.height, 2));
	}
    
    public String toString(){
    	String str = "lastupdate:\t";
    	str += this.lastupdate.toString();
    	str += "\n";
    	
    	str += "weight:\t";
    	str += this.weight;
    	str += "\n";
    	
    	str += "height:\t";
    	str += this.height;
    	str += "\n";
    	
    	str += "bmi:\t";
    	str += this.bmi;
    	str += "\n";    	
		return str;
		}

	/**
     * Recupera il valore della proprietà lastupdate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastupdate() {
        return lastupdate;
    }

    /**
     * Imposta il valore della proprietà lastupdate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastupdate(XMLGregorianCalendar value) {
        this.lastupdate = value;
    }

    /**
     * Recupera il valore della proprietà weight.
     * 
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Imposta il valore della proprietà weight.
     * 
     */
    public void setWeight(float value) {
        this.weight = value;
    }

    /**
     * Recupera il valore della proprietà height.
     * 
     */
    public double getHeight() {
        return height;
    }

    /**
     * Imposta il valore della proprietà height.
     * 
     */
    public void setHeight(float value) {
        this.height = value;
    }

    /**
     * Recupera il valore della proprietà bmi.
     * 
     */
    public double getBmi() {
        return bmi;
    }

    /**
     * Imposta il valore della proprietà bmi.
     * 
     */
    public void setBmi(float value) {
        this.bmi = value;
    }

}
