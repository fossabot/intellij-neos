/*
 *  IntelliJ IDEA plugin to support the Neos CMS.
 *  Copyright (C) 2016  Christian Vette
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.vette.idea.neos.lang.fusion.psi.impl.ext;

import com.intellij.lang.ASTNode;
import de.vette.idea.neos.lang.fusion.psi.*;
import de.vette.idea.neos.lang.fusion.psi.impl.FusionNamedElementImpl;
import org.jetbrains.annotations.NotNull;

public abstract class FusionPathImplMixin extends FusionNamedElementImpl implements FusionPath {
    public FusionPathImplMixin(@NotNull ASTNode astNode) {
        super(astNode);
    }

    public boolean isPrototypeSignature() {
        return findChildrenByType(FusionTypes.PATH_PART).isEmpty()
                && findChildrenByType(FusionTypes.PATH_SEPARATOR).isEmpty()
                && getValueStringSingleLine() == null
                && getMetaPropertyList().isEmpty()
                && getPrototypeSignatureList().size() == 1;
    }

    @Override
    public String getName() {
        if (getFirstChild() instanceof FusionPrototypeSignature) {
            FusionPrototypeSignature signature = ((FusionPrototypeSignature) getFirstChild());
            if (signature.getType() != null) {
                return signature.getType().getText();
            }
        }

        return this.getText();
    }
}
