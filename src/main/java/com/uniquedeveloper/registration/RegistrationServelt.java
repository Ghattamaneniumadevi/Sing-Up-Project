package com.uniquedeveloper.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegistrationServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname=request.getParameter("name");
		String uemail=request.getParameter("email");
		String upwd=request.getParameter("pass");
		String umobile=request.getParameter("contact");
		PrintWriter out=response.getWriter();
		out.print(uname);
		out.print(uemail);
		out.print(upwd);
		out.print(umobile);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/singup","root","root");
			PreparedStatement pst=con.prepareStatement("insert into users(uname,upwd,uemail,umobile)values(?,?,?,?)");
			pst.setString(1, uname);
			pst.setString(2, upwd);
			pst.setString(3, uemail);
			pst.setString(4, umobile);
			RequestDispatcher dispatcher=null;
			
			int rowcount=pst.executeUpdate();
			dispatcher=request.getRequestDispatcher("registration.jsp");
			if(rowcount>0) {
				request.setAttribute("status", "success");
			}
			else {
				request.setAttribute("status", "failed");
				
			}
			
		dispatcher.forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
