package com.pasan.medifinder.cloud.oauth.service.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class PersistentSetMixin {}