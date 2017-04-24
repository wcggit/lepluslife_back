package com.jifenke.lepluslive.barcode;

import net.glxn.qrgen.core.image.ImageType;

import org.krysalis.barcode4j.tools.UnitConv;

public class BarcodeConfig {

	public static class Barcode {
		private int dpi;

		private double height;

		private double barHeight;

		private double fontSize;

		private double moduleWidth;

		public int getDpi() {
			return dpi;
		}

		public void setDpi(int dpi) {
			this.dpi = dpi;
		}

		public double getHeight() {
			return height;
		}

		public void setHeight(double height) {
			this.height = height;
		}

		public double getBarHeight() {
			return barHeight;
		}

		public void setBarHeight(double barHeight) {
			this.barHeight = barHeight;
		}

		public double getFontSize() {
			return fontSize;
		}

		public void setFontSize(double fontSize) {
			this.fontSize = fontSize;
		}

		public double getModuleWidth() {
			return moduleWidth;
		}

		public void setModuleWidth(double moduleWidth) {
			this.moduleWidth = moduleWidth;
		}

		public static Barcode defaultConfig() {
			Barcode barcodeConfig = new Barcode();
			barcodeConfig.setDpi(300);
			barcodeConfig.setBarHeight(18);
			barcodeConfig.setHeight(64);
			barcodeConfig.setFontSize(1.6);
			barcodeConfig.setModuleWidth(UnitConv.in2mm(8.0f / barcodeConfig.getDpi()));
			return barcodeConfig;
		}
	}

	public static class QRCode {
		private int width;

		private int height;

		private ImageType imageType;

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public ImageType getImageType() {
			return imageType;
		}

		public void setImageType(ImageType imageType) {
			this.imageType = imageType;
		}

		public static QRCode defaultConfig() {
			QRCode qrConfig = new QRCode();
			qrConfig.setWidth(500);
			qrConfig.setHeight(500);
			qrConfig.setImageType(ImageType.JPG);
			return qrConfig;
		}
	}
}