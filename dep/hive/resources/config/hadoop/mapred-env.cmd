#!/usr/bin/env bash
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License. See accompanying LICENSE file.
#

# Set httpfs specific environment variables here.
#
# hadoop-env.sh is read prior to this file.
#

# HTTPFS config directory
#
# export HTTPFS_CONFIG=${HADOOP_CONF_DIR}

# HTTPFS log directory
#
# export HTTPFS_LOG=${HADOOP_LOG_DIR}

# HTTPFS temporary directory
#
# export HTTPFS_TEMP=${HADOOP_HDFS_HOME}/temp

# The HTTP port used by HTTPFS
#
# export HTTPFS_HTTP_PORT=14000

# The maximum number of HTTP handler threads
#
# export HTTPFS_MAX_THREADS=1000

# The hostname HttpFS server runs on
#
# export HTTPFS_HTTP_HOSTNAME=$(hostname -f)

# The maximum size of HTTP header
#
# export HTTPFS_MAX_HTTP_HEADER_SIZE=65536

# Whether SSL is enabled
#
# export HTTPFS_SSL_ENABLED=false

# The location of the SSL keystore if using SSL
#
# export HTTPFS_SSL_KEYSTORE_FILE=${HOME}/.keystore

# The password of the SSL keystore if using SSL
#
# export HTTPFS_SSL_KEYSTORE_PASS=passwordroot@hadoop-hadoop-hdfs-nn-1:/opt/hadoop-3.3.2# cat /opt/hadoop/etc/hadoop/mapred-env.cmd
@echo off
@rem Licensed to the Apache Software Foundation (ASF) under one or more
@rem contributor license agreements.  See the NOTICE file distributed with
@rem this work for additional information regarding copyright ownership.
@rem The ASF licenses this file to You under the Apache License, Version 2.0
@rem (the "License"); you may not use this file except in compliance with
@rem the License.  You may obtain a copy of the License at
@rem
@rem     http://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.

set HADOOP_JOB_HISTORYSERVER_HEAPSIZE=1000

set HADOOP_MAPRED_ROOT_LOGGER=%HADOOP_LOGLEVEL%,RFA
