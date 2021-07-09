//
//  Ktorio_Closeables+.swift
//  JokesApp
//
//  Created by Kurt Renzo Acosta on 7/10/21.
//  Copyright © 2021 Kurt. All rights reserved.
//

import Foundation
import JokesShared

extension Ktor_ioCloseable {
  func addToCloseables(_ viewModelWrapper: ViewModelWrapper) {
    viewModelWrapper.closeables.append(self)
  }
}
