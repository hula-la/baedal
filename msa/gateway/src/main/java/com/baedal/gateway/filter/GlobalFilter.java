//package com.baedal.gateway.filter;
//
//import lombok.Data;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//
//public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {
//    public GlobalFilter() {
//        super(Config.class);
//    }
//    @Override
//    public GatewayFilter apply(GlobalFilter.Config config) {
//        return null;
//    }
//
//    @Data
//    public static class Config {
//        private String baseMessage;
//        private boolean preLogger;
//        private boolean postLogger;
//    }
//
//}
