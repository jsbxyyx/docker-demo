package org.xxz.docker.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class HttpServer {
	
	private int port = 8080;
	
	@PostConstruct
	private void run() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)//
			.channel(NioServerSocketChannel.class)//
			.option(ChannelOption.SO_BACKLOG, 1024)//
			.option(ChannelOption.TCP_NODELAY, true)//
			.handler(new LoggingHandler(LogLevel.INFO))//
			.childOption(ChannelOption.SO_KEEPALIVE, true)//
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast("httpCodec",new HttpServerCodec());
                    ch.pipeline().addLast("httpObject",new HttpObjectAggregator(65536));
                    ch.pipeline().addLast("serverHandler",new ProxyHandler());
				}
			});
			
			ChannelFuture f = b.bind(port).sync();
			f.channel().closeFuture().sync();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

}
