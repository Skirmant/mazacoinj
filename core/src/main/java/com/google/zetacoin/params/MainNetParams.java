/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.zetacoin.params;

import com.google.zetacoin.core.NetworkParameters;
import com.google.zetacoin.core.Utils;

import static com.google.common.base.Preconditions.checkState;

/**
 * Parameters for the main production network on which people trade goods and services.
 */
public class MainNetParams extends NetworkParameters {
    public MainNetParams() {
        super();
        interval = INTERVAL;
        targetTimespan = TARGET_TIMESPAN;
        // Note: proofOfWorkLimit is copied from litecoinj only because
        // - zetacoin satoshi client's bnProofOfWorkLimit is the same as litecoin satoshi client's
        proofOfWorkLimit = Utils.decodeCompactBits(0x1d03ffffL); 
        dumpedPrivateKeyHeader = 224;
        addressHeader = 50;
        p2shHeader = 9;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        port = 12835;
        packetMagic = 0xf8b503dfL; // 0xF8, 0xB5, 0x03, 0xDF
        genesisBlock.setDifficultyTarget(0x1e0ffff0L);
        genesisBlock.setTime(1390747675L);
        genesisBlock.setNonce(2091390249);
        id = ID_MAINNET;
        subsidyDecreaseBlockCount = 241920;
        spendableCoinbaseDepth = 100;
        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals("00000c7c73d8ce604178dae13f0fc6ec0be3275614366d44b1b4b5c6e238c60c"),genesisHash);

        // This contains (at a minimum) the blocks which are not BIP30 compliant. BIP30 changed how duplicate
        // transactions are handled. Duplicated transactions could occur in the case where a coinbase had the same
        // extraNonce and the same outputs but appeared at different heights, and greatly complicated re-org handling.
        // Having these here simplifies block connection logic considerably.
        // checkpoints.put(91722, new Sha256Hash("00000000000271a2dc26e7667f8419f2e15416dc6955e5a6c6cdf3f2574dd08e"));
        // checkpoints.put(91812, new Sha256Hash("00000000000af0aed4792b1acee3d966af36cf5def14935db8de83d6f9306f2f"));
        // checkpoints.put(91842, new Sha256Hash("00000000000a4d0a398161ffc163c503763b1f4360639393e0e4c8e300e0caec"));
        // checkpoints.put(91880, new Sha256Hash("00000000000743f190a18c5577a3c2d2a1f610ae9601ac046a38084ccb7cd721"));
        // checkpoints.put(200000, new Sha256Hash("000000000000034a7dedef4a161fa058a2d67a173a90155f3a2fe6fc132e0ebf"));
        // TODO: Is ZetaCoin post-BIP30? Do we need these?

        dnsSeeds = new String[] {
          "explorer.embargocoin.com",
          "maza.cryptoadhd.com",
          "107.170.173.232",
          "70.44.99.51",
          "82.226.151.241",
          "54.201.30.196",
          "24.188.5.17",
          "98.202.20.45",
          "46.7.42.152"
        };
    }

    private static MainNetParams instance;
    public static synchronized MainNetParams get() {
        if (instance == null) {
            instance = new MainNetParams();
        }
        return instance;
    }

    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_MAINNET;
    }
}
