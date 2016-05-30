/**
 *
 */
package com.halfcigarette.dietitian.ui.activity;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class ClientThread implements Runnable
{
	private Socket s;
	// ������UI�̷߳�����Ϣ��Handler����
	private Handler handler;
	// �������UI�̵߳���Ϣ��Handler����
	public Handler revHandler;
	// ���߳��������Socket����Ӧ��������
	BufferedReader br = null;
	OutputStream os = null;

	public ClientThread(Handler handler)
	{
		this.handler = handler;
	}

	public void run()
	{
		try
		{
			s = new Socket("192.168.43.161", 8055);
			br = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
			os = s.getOutputStream();
			// ����һ�����߳�����ȡ��������Ӧ������
			new Thread()
			{
				@Override
				public void run()
				{
					String content = null;
					// ���϶�ȡSocket�������е����ݡ�
					try
					{
						while ((content = br.readLine()) != null)
						{
							// ÿ���������Է�����������֮�󣬷�����Ϣ֪ͨ���������ʾ������
							Message msg = new Message();
							msg.what = 0x123;
							msg.obj = content;
							handler.sendMessage(msg);
						}
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}.start();
			// Ϊ��ǰ�̳߳�ʼ��Looper
			Looper.prepare();
			// ����revHandler����
			revHandler = new Handler()
			{
				@Override
				public void handleMessage(Message msg)
				{
					// ���յ�UI�߳����û����������
					if (msg.what == 0x345)
					{
						// ���û����ı��������������д������
						try
						{
							os.write((msg.obj.toString() + "\r\n")
								.getBytes("utf-8"));
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}

				}
			};
			// ����Looper
			Looper.loop();
		}
		catch (SocketTimeoutException e1)
		{
//			System.out.println("�������ӳ�ʱ����");
			e1.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

