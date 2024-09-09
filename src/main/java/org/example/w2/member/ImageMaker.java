package org.example.w2.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(value = "/capcha")
@Log4j2
public class ImageMaker  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("image/jpeg");

        BufferedImage image
                = new BufferedImage(300, 200, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();
        g.setColor(Color.PINK);
        g.fillRect(0, 0, 300, 200);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));

        int num = (int)(Math.random() * 10000); // 0 ~ 9999

        HttpSession session = req.getSession();
        session.setAttribute("num", num);

        g.drawString("" + num,10,100);

        @Cleanup OutputStream out = resp.getOutputStream();

        ImageIO.write(image, "jpeg",out);

    }
}
