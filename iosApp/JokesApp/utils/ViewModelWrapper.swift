//
//  BaseObservableObject.swift
//  JokesApp
//
//  Created by Kurt Renzo Acosta on 7/10/21.
//  Copyright © 2021 Kurt. All rights reserved.
//

import Foundation
import JokesShared

class ViewModelWrapper {
  var closeables: [Ktor_ioCloseable] = []
  
  deinit {
    closeables.forEach { closeable in
      closeable.close()
    }
  }
}
