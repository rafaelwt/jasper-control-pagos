package com.reportes.jasper.resource;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
@RestController
@RequestMapping("/rest/reports")
public class ReportsResource {
  // String url = "jdbc:postgresql://vps229753.vps.ovh.ca:5432/db_presupuesto";
  // String contrasena = "123456Zxcv";

  String url = "jdbc:sqlserver://localhost:1433;database=db_controlpagos";
  // String contrasena = "postgres";
  String contrasena = "123456Zxcv";

  public ReportsResource() {

  }

  @CrossOrigin
  @GetMapping("/llamadasporusuario")
  public void llamadasporusuario(HttpServletResponse response, @RequestParam Integer cod_usuario, @RequestParam String fecha_ini, @RequestParam String fecha_fin
     ) throws Exception {
    try {

      String connectionUrl =""+url+";"
      + "user=sa;"
      + "password="+contrasena+";"
      // + "encrypt=true;"
      + "trustServerCertificate=false;"
      + "loginTimeout=30;";
      
      try (Connection conn = DriverManager.getConnection(connectionUrl);
      Statement statement = conn.createStatement();) {
        response.setContentType("application/pdf");
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/rpt_llamada_por_usuario.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
  
        HashMap params = new HashMap();
        params.put("cod_usuario", cod_usuario);
        params.put("fecha_ini", fecha_ini);
        params.put("fecha_fin", fecha_fin);
  
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);
  
        // JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null,
        // conn);
        JRPdfExporter exporter = new JRPdfExporter();
  
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        // exporter.setExporterOutput(new
        // SimpleOutputStreamExporterOutput("userReport.pdf")); //genera un pdf en la
        // ruta raiz
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);
  
        SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
        exportConfig.setMetadataAuthor("baeldung");
        exportConfig.setEncrypted(true);
        exportConfig.setAllowedPermissionsHint("PRINTING");
  
        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exportConfig);
  
        exporter.exportReport();

      }
      catch (SQLException e) {
      e.printStackTrace();
      }

    } catch (Exception exc) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al generar el reporte", null);
    }

  }

  
  @CrossOrigin
  @GetMapping("/condicionpago")
  public void condicionpago(HttpServletResponse response, @RequestParam Integer cod_usuario, @RequestParam String fecha_ini, @RequestParam String fecha_fin
     ) throws Exception {
    try {

      String connectionUrl =""+url+";"
      + "user=sa;"
      + "password="+contrasena+";"
      // + "encrypt=true;"
      + "trustServerCertificate=false;"
      + "loginTimeout=30;";
      
      try (Connection conn = DriverManager.getConnection(connectionUrl);
      Statement statement = conn.createStatement();) {
        response.setContentType("application/pdf");
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/rpt_condicion_pago.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
  
        HashMap params = new HashMap();
        params.put("cod_usuario", cod_usuario);
        params.put("fecha_ini", fecha_ini);
        params.put("fecha_fin", fecha_fin);
  
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);
  
        // JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null,
        // conn);
        JRPdfExporter exporter = new JRPdfExporter();
  
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        // exporter.setExporterOutput(new
        // SimpleOutputStreamExporterOutput("userReport.pdf")); //genera un pdf en la
        // ruta raiz
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);
  
        SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
        exportConfig.setMetadataAuthor("baeldung");
        exportConfig.setEncrypted(true);
        exportConfig.setAllowedPermissionsHint("PRINTING");
  
        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exportConfig);
  
        exporter.exportReport();

      }
      catch (SQLException e) {
      e.printStackTrace();
      }

    } catch (Exception exc) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al generar el reporte", null);
    }

  }

  
  @CrossOrigin
  @GetMapping("/fechasposiblepago")
  public void fechasposiblepago(HttpServletResponse response, @RequestParam Integer cod_usuario, @RequestParam String fecha_ini, @RequestParam String fecha_fin
     ) throws Exception {
    try {

      String connectionUrl =""+url+";"
      + "user=sa;"
      + "password="+contrasena+";"
      // + "encrypt=true;"
      + "trustServerCertificate=false;"
      + "loginTimeout=30;";
      
      try (Connection conn = DriverManager.getConnection(connectionUrl);
      Statement statement = conn.createStatement();) {
        response.setContentType("application/pdf");
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/rpt_fechas_posible_pago.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
  
        HashMap params = new HashMap();
        params.put("cod_usuario", cod_usuario);
        params.put("fecha_ini", fecha_ini);
        params.put("fecha_fin", fecha_fin);
  
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);
  
        // JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null,
        // conn);
        JRPdfExporter exporter = new JRPdfExporter();
  
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        // exporter.setExporterOutput(new
        // SimpleOutputStreamExporterOutput("userReport.pdf")); //genera un pdf en la
        // ruta raiz
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);
  
        SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
        exportConfig.setMetadataAuthor("baeldung");
        exportConfig.setEncrypted(true);
        exportConfig.setAllowedPermissionsHint("PRINTING");
  
        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exportConfig);
  
        exporter.exportReport();

      }
      catch (SQLException e) {
      e.printStackTrace();
      }

    } catch (Exception exc) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al generar el reporte", null);
    }

  }

  @RequestMapping("/test")
  public Object test() 
  {

          HashMap<String, Object> map = new HashMap<>();
          map.put("status", "ok");
          map.put("message", "Hello wordl");
          return map;
  }

}
