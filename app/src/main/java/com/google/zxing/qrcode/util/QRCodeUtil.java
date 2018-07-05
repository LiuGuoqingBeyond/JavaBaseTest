package com.google.zxing.qrcode.util;

import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class QRCodeUtil {

	/**
	 * 生成二维码信息
	 * @param text 二维码数据
	 * @param width 二维码宽度
	 * @param height 二维码高度
	 * @param icon 二维码图标
	 * @return
	 */
	public static Bitmap createImage(String text, int width, int height, Bitmap icon) {
		try {
			if (text == null || "".equals(text) || text.length() < 1) {
				return null;
			}
			
			// 需要引入core包
			QRCodeWriter writer = new QRCodeWriter();

			// 把输入的文本转为二维码
			BitMatrix martix = writer.encode(text, BarcodeFormat.QR_CODE, width, height);

			Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
			int[] pixels = new int[width * height];
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					if (bitMatrix.get(x, y)) {
						pixels[y * width + x] = 0xff404040;
					} else {
						pixels[y * width + x] = 0xffffffff;
					}
				}
			}

			Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
			bitmap.setPixels(pixels, 0, width, 0, 0, width, height);

			return setQRCodeIcon(bitmap, icon);
		} catch (WriterException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将二维码设置图标
	 * @param bitmap 二维码图片
	 * @param icon 二维码图标
	 * @return
	 */
	private static Bitmap setQRCodeIcon(Bitmap bitmap, Bitmap icon) {
		int logoWidth = bitmap.getWidth() / 5; // 设置icon图片宽度为二维码图片的五分之一
		int logoHeight = bitmap.getHeight() / 5; // 设置icon图片高度为二维码图片的五分之一
		icon = BitmapHelper.resizeImage(icon, logoWidth, logoHeight);
		int logoX = (bitmap.getWidth() - logoWidth) / 2; // 设置icon图片的位置,这里令其居中
		int logoY = (bitmap.getHeight() - logoHeight) / 2; // 设置icon图片的位置,这里令其居中
		int right = logoX + logoWidth;
		int bottom = logoY + logoHeight;

		Canvas canvas = new Canvas(bitmap);

		Rect mRectLogo = new Rect(0, 0, logoWidth, logoHeight);
		Rect mRect = new Rect(logoX, logoY, right, bottom);
		canvas.drawBitmap(icon, mRectLogo, mRect, null);

		return bitmap;
	}
}
