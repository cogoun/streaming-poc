FROM grafana/grafana:latest

ADD  servers/grafana/plugins /var/lib/grafana/plugins
COPY servers/grafana/dashboards/dashboards.yaml /etc/grafana/provisioning/dashboards
COPY servers/grafana/datasources/influxdb.yaml /etc/grafana/provisioning/datasources
RUN  mkdir /var/lib/grafana/dashboards
COPY servers/grafana/dashboards/streaming-dashboard.json /var/lib/grafana/dashboards
