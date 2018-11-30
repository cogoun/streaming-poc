FROM telegraf:latest
COPY servers/telegraf/telegraf.conf /etc/telegraf/telegraf.conf
