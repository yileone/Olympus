
package fusioncharts;

import java.util.*;

public class FusionCharts {

	private String constructorTemplate = "<script type=\"text/javascript\">FusionCharts.ready(function () {new FusionCharts(__constructorOptions__);});</script>";
	private String renderTemplate = "<script type=\"text/javascript\">FusionCharts.ready(function () {                FusionCharts(\"__chartId__\").render();});</script>";
	private String[] chartOptions = new String[10];
	private int eventPresent = 0;
	private String chartDataSource = "";
	private String eventString = new String();

	public FusionCharts(String type, String id, String width, String height, String renderAt, String dataFormat,
			String dataSource) {
		chartOptions[0] = id;
		chartOptions[1] = width;
		chartOptions[2] = height;
		chartOptions[3] = renderAt;
		chartOptions[4] = type;
		chartOptions[5] = dataFormat;
		if (chartOptions[5].contains("url")) {
			chartOptions[6] = "\"" + dataSource + "\"";
		} else {
			chartOptions[6] = "__dataSource__";
			chartDataSource = addSlashes(dataSource.replaceAll("\n", ""));
		}
	}

	// Overloaded wrapper constructor supporting Multiple Events.
	// Event name(s) and Event function(s) must be provided to the wrapper
	// constructor in the form of a HashMap object.
	// The HashMap object must have the eventname-eventfunction mapped as key-value
	// pair.
	public FusionCharts(String type, String id, String width, String height, String renderAt, String dataFormat,
			String dataSource, HashMap<String, String> events) {
		chartOptions[0] = id;
		chartOptions[1] = width;
		chartOptions[2] = height;
		chartOptions[3] = renderAt;
		chartOptions[4] = type;
		chartOptions[5] = dataFormat;
		if (chartOptions[5].contains("url")) {
			chartOptions[6] = "\"" + dataSource + "\"";
		} else {
			chartOptions[6] = "__dataSource__";
			chartDataSource = addSlashes(dataSource.replaceAll("\n", ""));
			// System.out.println("*******************************************************");
			// System.out.println(this.chartDataSource);
		}

		// Iterating the keys(Event names) from the HashMap object parameter.
		// Eliminate event names that does not have its corresponding event function
		// mapped in the HashMap object.
		// Adds the "event" code in supported format(JSON) to the eventString variable.
		// This eventString variable is added in the jsonEncode() method.
		for (String key : events.keySet()) {
			if (events.get(key) != "") {
				eventPresent = 1;
				eventString += "\"" + key + "\":function(eventObj,dataObj)" + events.get(key) + ",";
				// System.out.println(events.get(key));
			}
		}

		// The eventString variable is updated with the supported JSON format or with
		// null string,
		// If the presence of valid event is detected or not detected respectively.
		if (eventPresent == 0) {
			eventString = "";
		} else {
			eventString = ",events:{" + eventString + "}";
		}

		// System.out.println("Final string : " + eventString);

	}

	private String addSlashes(String str) {
		str = str.replaceAll("\n", "");
		str = str.replaceAll("\\\\", "\\\\\\\\");
		str = str.replaceAll("\\n", "\\\\n");
		str = str.replaceAll("\\r", "\\\\r");
		str = str.replaceAll("\\00", "\\\\0");
		str = str.replaceAll("u003d", "=");
		str = str.replaceAll("'", "\\\\'");
		str = str.replaceAll("\\\\", "");
		str = str.replaceAll("\"\\{", "{");
		str = str.replaceAll("\"\\[", "[");
		str = str.replaceAll("\\}\\]\"", "}]");
		str = str.replaceAll("\"\\}\"", "\"}");
		str = str.replaceAll("\\}\"\\}", "}}");
		str = str.replaceAll("u0027", "\'");
		str = str.replaceAll("u003c", "<");
		str = str.replaceAll("u003e", ">");
		str = str.replaceAll("u0026", "&");
		str = str.replaceAll("\",\"MIvalue\"", ",\"value\"");
		str = str.replaceAll("\"function", "function");

		return str;
	}

	private String jsonEncode(String[] data) {

		String json = new String();
		json = "{type: \"" + chartOptions[4] + "\",renderAt: \"" + chartOptions[3] + "\",width: \"" + chartOptions[1]
				+ "\",height: \"" + chartOptions[2] + "\",dataFormat: \"" + chartOptions[5] + "\",id: \""
				+ chartOptions[0] + "\",dataSource: " + chartOptions[6] + eventString + "}";

		return json;
	}

	public String render() {
		String outputHTML;
		if (chartOptions[5].contains("url")) {
			outputHTML = constructorTemplate.replace("__constructorOptions__", jsonEncode(chartOptions))
					+ renderTemplate.replace("__chartId__", chartOptions[0]);
		} else {
			if ("json".equals(chartOptions[5])) {
				outputHTML = constructorTemplate.replace("__constructorOptions__",
						jsonEncode(chartOptions).replace("__dataSource__", chartDataSource))
						+ renderTemplate.replace("__chartId__", chartOptions[0]);
			} else {
				outputHTML = constructorTemplate.replace("__constructorOptions__",
						jsonEncode(chartOptions).replace("__dataSource__", "\'" + chartDataSource + "\'"))
						+ renderTemplate.replace("__chartId__", chartOptions[0]);
			}
		}
		// System.out.println("*******************************************************");
		// outputHTML ="<script type=\"text/javascript\">FusionCharts.ready(function ()
		// {new FusionCharts({type: \"multiaxisline\",renderAt: \"chart\",width:
		// \"90%\",height: \"600\",dataFormat: \"json\",id: \"chart1\",dataSource: {
		// \"chart\": { \"caption\": \"Hogar-TES \",\"xaxisname\":
		// \"null\",\"yaxisname\": \"null\",\"showvalues\": \"0\",\"numberprefix\":
		// \"S\",\"legendborderalpha\": \"50\",\"showborder\": \"0\",\"bgcolor\":
		// \"FFFFFF,FFFFFF\",\"plotgradientcolor\": \" \",\"showalternatehgridcolor\":
		// \"0\",\"showplotborder\": \"0\",\"labeldisplay\": \"WRAP\",\"divlinecolor\":
		// \"CCCCCC\",\"showcanvasborder\": \"0\",\"canvasborderalpha\":
		// \"0\",\"legendshadow\": \"0\",\"linethickness\": \"3\"},\"categories\":[ {
		// \"category\": [{ \"label\": \"2017-10-12 22:15:00\"},{ \"label\":
		// \"2017-10-12 22:20:00\"},{ \"label\": \"2017-10-12 22:25:00\"},{ \"label\":
		// \"2017-10-12 22:30:00\"},{ \"label\": \"2017-10-12 22:35:00\"},{ \"label\":
		// \"2017-10-12 22:40:00\"}] } ],\"axis\": [ { \"title\": \"Revenue\",
		// \"titlepos\": \"left\",\"tickwidth\": \"10\",\"numberPrefix\":
		// \"$\",\"divlineisdashed\": \"1\",\"dataset\": [{\"seriesname\":
		// \"Entrada\",\"data\": [ { \"value\":\"1.541\" }, { \"value\":\"1.517\" }, {
		// \"value\":\"1497600.0\" }, { \"value\":\"1371050.0\" }, {
		// \"value\":\"1353490.0\" }, { \"value\":\"1472750.0\" }]},{\"seriesname\":
		// \"Salida\", \"data\": [ { \"value\":\"1155730.0\" }, {
		// \"value\":\"1126620.0\" }, { \"value\":\"1.108\" }, { \"value\":\"1006640.0\"
		// }, { \"value\":\"1001610.0\" }, { \"value\":\"1.079\"
		// }]}]}]}});});</script><script
		// type=\"text/javascript\">FusionCharts.ready(function () {
		// FusionCharts(\"chart1\").render();});</script>";
		// System.out.println(outputHTML);
		return outputHTML;
	}

}
