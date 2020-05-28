package managedbean;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import model.Event;
import model.Ticket;


@Named
@SessionScoped
@RequestScoped
public class ChartJsView implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PieChartModel pieModel;
	 private BarChartModel barModel;
     
	
     
    
    @EJB
	services.EventService EventService;
   
    @EJB
   	services.TicketService TicketService;
 
    @PostConstruct
    public void init() {
        createPieModel();
        
        createBarModel();
     
   
    }
     
    private void createPieModel() {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();
        
        long nbrWorkshop=EventService.getNumberEventWorkshop();
       long nbrStand=EventService.getNumberEventStand();
        long nbrSales=EventService.getNumberEventSales();
        long nbrConferance=EventService.getNumberEventConferance();
        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(nbrWorkshop);
        values.add(nbrStand);
        values.add(nbrSales);
        values.add(nbrConferance);
        dataSet.setData(values);
         
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(75, 192, 192)");
        dataSet.setBackgroundColor(bgColors);
         
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Workshop");
        labels.add("Stand");
        labels.add("Sales");
        labels.add("Conferance");
        data.setLabels(labels);
         
        pieModel.setData(data);
        
        
    
    }
     

     
    
    public void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();
         
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel(" Number of Ticket By Event");
         
        
        List<Number> values = new ArrayList<>();
        List<Event> eventsList = EventService.getAllEvents();
        for(Event e :eventsList)
        	{
        // int	nbrTicket =TicketService.getNumberOfTicketByEvent(e.getEventId());
        	int nbrTicket=e.getTickets().size();
       
        	values.add(nbrTicket);
        
         	}
        
        
        
   //     List<Number> values = new ArrayList<>();
     //   values.add(65);
      //  values.add(59);
      //  values.add(80);
      //  values.add(81);
       // values.add(56);
        //values.add(55);
       // values.add(40);
        barDataSet.setData(values);
         
     //   List<String> bgColor = new ArrayList<>();
       // bgColor.add("rgba(255, 99, 132, 0.2)");
        //bgColor.add("rgba(255, 159, 64, 0.2)");
       // bgColor.add("rgba(255, 205, 86, 0.2)");
       // bgColor.add("rgba(75, 192, 192, 0.2)");
      //  bgColor.add("rgba(54, 162, 235, 0.2)");
      //  bgColor.add("rgba(153, 102, 255, 0.2)");
      //  bgColor.add("rgba(201, 203, 207, 0.2)");
      //  barDataSet.setBackgroundColor(bgColor);
         
      //  List<String> borderColor = new ArrayList<>();
       // borderColor.add("rgb(255, 99, 132)");
        //borderColor.add("rgb(255, 159, 64)");
        //borderColor.add("rgb(255, 205, 86)");
      //  borderColor.add("rgb(75, 192, 192)");
     //   borderColor.add("rgb(54, 162, 235)");
     //   borderColor.add("rgb(153, 102, 255)");
     //   borderColor.add("rgb(201, 203, 207)");
      //  barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);
         
        data.addChartDataSet(barDataSet);
         
        
        List<String> labels = new ArrayList<>();
        for(Event e :eventsList)
    	{
   
   
        	 labels.add(e.getTitleEvent());
    
     	}
        
        
       // List<String> labels = new ArrayList<>();
     //   labels.add("January");
       // labels.add("February");
       // labels.add("March");
       // labels.add("April");
       // labels.add("May");
        //labels.add("June");
       // labels.add("July");
        data.setLabels(labels);
        barModel.setData(data);
         
        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
         
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart");
        options.setTitle(title);
 
        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);
 
        barModel.setOptions(options);
    }
     
 
     
   
 
    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", DataSet Index:" + event.getDataSetIndex());
 
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public PieChartModel getPieModel() {
        return pieModel;
    }
 
    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }
    
    
    public BarChartModel getBarModel() {
        return barModel;
    }
 
    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
 

    
    

 
    }
