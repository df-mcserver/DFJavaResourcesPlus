package uk.co.nikodem.DFJavaResourcesPlus;

import team.unnamed.creative.ResourcePack;

public interface AssetSearcher {
    String getAssetTypeName();
    void run(ResourcePack resourcePack);
}
